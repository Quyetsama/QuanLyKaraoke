import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SanPhamManager {
    public static Scanner scanner = new Scanner(System.in);
    private List<SanPham> sanphamList;
    private SanPhamDAO sanphamDAO;

    public SanPhamManager() {
        sanphamDAO = new SanPhamDAO();
        sanphamList = sanphamDAO.read();
    }

    public boolean CheckMNCC(String mnv){
        NhaCungCapManager managerNCC = new NhaCungCapManager();

        for(int i = 0; i < managerNCC.getNhaCungCapList().size(); i++){
            if(managerNCC.getNhaCungCapList().get(i).getMncc().equals(mnv)){
                return true;
            }
        }
        System.out.println("Mã nhà cung cấp không tồn tại, vui lòng nhập lại!");
        return false;
    }

    public void Add() {
        String msp;

        System.out.print("Mã sản phẩm: ");
        String[] s = null;
        if(sanphamList.size() == 0){
            msp = "SP1";
        }
        else{
            s = sanphamList.get(sanphamList.size()-1).getMsp().split("SP");
            msp = "SP" + (Integer.parseInt(s[1]) + 1);
        }
        System.out.println(msp);
        System.out.print("Tên sản phẩm: ");
        String tenSP = scanner.nextLine();

        System.out.print("Số lượng: ");
        int soLuong = scanner.nextInt();

        System.out.print("Đơn giá: ");
        float donGia = scanner.nextFloat();

        scanner.nextLine();

        System.out.print("Nhà cung cấp (");
        NhaCungCapManager managerNCC = new NhaCungCapManager();
        String mncc;


        for(int i = 0; i < managerNCC.getNhaCungCapList().size(); i++){
            if(i==0){
                System.out.print("" + managerNCC.getNhaCungCapList().get(i).getMncc());
            }else {
                System.out.print(", " + managerNCC.getNhaCungCapList().get(i).getMncc());
            }
        }
        System.out.print("): ");
        do{
            mncc = scanner.nextLine();
        }while (!CheckMNCC(mncc));

        SanPham sp = new SanPham(msp, tenSP, soLuong, donGia, mncc);
        sanphamList.add(sp);
        sanphamDAO.write(sanphamList);
    }

    public void Edit(String msp) {
        boolean isExisted = false;
        int size = sanphamList.size();
        for (int i = 0; i < size; i++) {
            if(sanphamList.get(i).getMsp().equals(msp)) {
                isExisted = true;

                SanPham sp;

                String tenSP;
                String sl;
                String donGia;
                String mncc;



                System.out.print("Tên sản phẩm - nhập 'k' để bỏ qua: "); tenSP = scanner.nextLine();
                if(tenSP.equals("k")){
                    tenSP = sanphamList.get(i).getTenSP();
                }

                System.out.print("Số lượng - nhập 'k' để bỏ qua: "); sl = scanner.nextLine();
                if(sl.equals("k")){
                    sl = String.valueOf(sanphamList.get(i).getSoLuong());
                }


                System.out.print("Đơn giá - nhập 'k' để bỏ qua: "); donGia = scanner.nextLine();
                if(donGia.equals("k")){
                    donGia = String.valueOf(sanphamList.get(i).getDonGia());
                }


                System.out.print("Nhà cung cấp - nhập 'k' để bỏ qua (");
                NhaCungCapManager managerNCC = new NhaCungCapManager();


                for(int j = 0; j < managerNCC.getNhaCungCapList().size(); j++){
                    if(j==0){
                        System.out.print("" + managerNCC.getNhaCungCapList().get(j).getMncc());
                    }else {
                        System.out.print(", " + managerNCC.getNhaCungCapList().get(j).getMncc());
                    }
                }
                System.out.print("): ");
                do{
                    mncc = scanner.nextLine();

                    if(mncc.equals("k")){
                        break;
                    }
                }while (!CheckMNCC(mncc));

                if(mncc.equals("k")){
                    mncc = sanphamList.get(i).getMancc();
                }



                sp = new SanPham(msp, tenSP, Integer.parseInt(sl), Float.parseFloat(donGia), mncc);


                sanphamList.set(i,sp);

                break;
            }
        }
        if(!isExisted) {
            System.out.println("Mã sản phẩm không hợp lê!");
        }else {
            sanphamDAO.write(sanphamList);
        }
    }

    public void Delete(String msp) {
        SanPham sp = null;
        int size = sanphamList.size();
        for (int i = 0; i < size; i++) {
            if(sanphamList.get(i).getMsp().equals(msp)) {
                sp = sanphamList.get(i);
                break;
            }
        }
        if(sp != null) {
            sanphamList.remove(sp);
            sanphamDAO.write(sanphamList);
        }
        else {
            System.out.println("Mã sản phẩm không hợp lê!");
        }
    }

    public void SortSanPhamPrice() {
        List<SanPham> listSort = new ArrayList<>(sanphamList);
        for(int i = 0 ; i < listSort.size() ; i ++){
            for( int j = listSort.size() - 1 ; j > i ; j --){
                if(listSort.get(j).getDonGia() > listSort.get(i).getDonGia()){
                    SanPham tmp = listSort.get(j);
                    listSort.set(j, listSort.get(i));
                    listSort.set(i, tmp);

                }
            }
        }

        // Ghi danh sách đã sắp xếp vào file
        sanphamDAO.write(listSort, "sortSanPhamPrice.txt");

        System.out.println("______________________Danh sách sản phẩm______________________");
        System.out.format("%20s %20s %20s %20s %20s \n", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Nhà cung cấp");
        for (SanPham sanPham : listSort) {
            sanPham.Show();
        }
    }

    public void SortSanPhamName(){
        List<SanPham> listSort = new ArrayList<>(sanphamList);
        for(int i = 0 ; i < listSort.size() ; i ++){
            for( int j = listSort.size() - 1 ; j > i ; j --){
                if(listSort.get(j).getTenSP().compareTo(listSort.get(i).getTenSP()) < 0){
                    SanPham tmp = listSort.get(j);
                    listSort.set(j, listSort.get(i));
                    listSort.set(i, tmp);

                }
            }
        }

        // Ghi danh sách đã sắp xếp vào file
        sanphamDAO.write(listSort, "SortSanPhamName.txt");

        System.out.println("______________________Danh sách sản phẩm______________________");
        System.out.format("%20s %20s %20s %20s %20s \n", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Nhà cung cấp");
        for (SanPham sanPham : listSort) {
            sanPham.Show();
        }
    }

    public void FindMSP(String msp){
        System.out.println("______________________Danh sách tìm kiếm sản phẩm______________________");
        System.out.format("%20s %20s %20s %20s %20s \n", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Nhà cung cấp");

        List<SanPham> listFind = new ArrayList<>(sanphamList);
        for(int i = 0 ; i < listFind.size() ; i ++){
            if(listFind.get(i).getMsp().contains(msp)){
                listFind.get(i).Show();
            }
        }
    }

    public void FindName(String name){
        System.out.println("______________________Danh sách tìm kiếm sản phẩm______________________");
        System.out.format("%20s %20s %20s %20s %20s \n", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Nhà cung cấp");

        List<SanPham> listFind = new ArrayList<>(sanphamList);
        for(int i = 0 ; i < listFind.size() ; i ++){
            if(listFind.get(i).getTenSP().contains(name)){
                listFind.get(i).Show();
            }
        }
    }

    public void show() {
        System.out.println("______________________Danh sách sản phẩm______________________");
        System.out.format("%20s %20s %20s %20s %20s \n", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Nhà cung cấp");
        for (SanPham sp : sanphamList) {
            sp.Show();

        }
    }

}
