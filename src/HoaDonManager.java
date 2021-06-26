import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HoaDonManager {
    public static Scanner scanner = new Scanner(System.in);
    private List<HoaDon> hoaDonList;
    private HoaDonDAO hoaDonDAO;

    public HoaDonManager() {
        hoaDonDAO = new HoaDonDAO();
        hoaDonList = hoaDonDAO.read();
    }

    public boolean CheckMNV(String mnv){
        NhanVienManager managerNV = new NhanVienManager();

        for(int i = 0; i < managerNV.getNhanVienList().size(); i++){
            if(managerNV.getNhanVienList().get(i).getMnv().equals(mnv)){
                return true;
            }
        }
        System.out.println("Mã nhân viên không tồn tại, vui lòng nhập lại!");
        return false;
    }

    public void Add() {
        String mhd;

        System.out.print("Mã hóa đơn: ");
        String[] s = null;
        if(hoaDonList.size() == 0){
            mhd = "HD1";
        }
        else{
            s = hoaDonList.get(hoaDonList.size()-1).getMhd().split("HD");
            mhd = "HD" + (Integer.parseInt(s[1]) + 1);
        }
        System.out.println(mhd);

        
        NhanVienManager managerNV = new NhanVienManager();
        String mnv;
        String dsNV = "";

        for(int i = 0; i < managerNV.getNhanVienList().size(); i++){
            if(i==0){
                dsNV += "" + managerNV.getNhanVienList().get(i).getMnv();
            }else {
                dsNV += ", " + managerNV.getNhanVienList().get(i).getMnv();
            }
        }
        
        
        
        System.out.print("Nhân viên lập hóa đơn (" + dsNV + "): ");
        do{
            mnv = scanner.nextLine();
        }while (!CheckMNV(mnv));






        System.out.print("Số phòng: ");
        int sPhong = nhapSo();

        System.out.print("Loại phòng (0: Thường 50K/h, 1: VIP 70K/h): ");
        int lPhong = nhapSo();

        System.out.print("Số giờ: ");
        int sGio = nhapSo();
        



        HoaDon hd = new HoaDon(mhd, sPhong, lPhong, sGio, mnv);
        hoaDonList.add(hd);
        hoaDonDAO.write(hoaDonList);
    }

    public void Edit(String mhd) {
        boolean isExisted = false;
        int size = hoaDonList.size();
        for (int i = 0; i < size; i++) {
            if(hoaDonList.get(i).getMhd().equals(mhd)) {
                isExisted = true;

                HoaDon hd;

                String sPhong1, lPhong1, sGio1, mnv;
                int sPhong, lPhong, sGio;


                
                NhanVienManager managerNV = new NhanVienManager();
                String dsNV = "";


                for(int j = 0; j < managerNV.getNhanVienList().size(); j++){
                    if(j==0){
                        dsNV += "" + managerNV.getNhanVienList().get(j).getMnv();
                    }else {
                        dsNV += ", " + managerNV.getNhanVienList().get(j).getMnv();
                    }
                }

                System.out.print("Nhân viên lập hóa đơn - nhập 'k' để bỏ qua (" + dsNV + "): ");
                do{
                    mnv = scanner.nextLine();

                    if(mnv.equals("k")){
                        break;
                    }
                }while (!CheckMNV(mnv));

                if(mnv.equals("k")){
                    mnv = hoaDonList.get(i).getMnv();
                }

                System.out.print("Số phòng - nhập 'k' để bỏ qua: ");
                
                while(true){
                    try{
                        sPhong1 = scanner.nextLine();
                        if(sPhong1.equals("k")){
                            sPhong = hoaDonList.get(i).getsPhong();
                        }else{
                            sPhong = Integer.parseInt(sPhong1);
                        }
                        break;
                    }catch(NumberFormatException ex){
                        System.out.println("Lỗi: ");
                    }
                }
                
                

                System.out.print("Loại phòng - nhập 'k' để bỏ qua: ");
                while(true){
                    try{
                        lPhong1 = scanner.nextLine();
                        if(lPhong1.equals("k")){
                            lPhong = hoaDonList.get(i).getLphong();
                        }else{
                            lPhong = Integer.parseInt(lPhong1);
                        }
                        break;
                    }catch(NumberFormatException ex){
                        System.out.print("Lỗi định dạng, vui lòng nhập lại: ");
                    }
                }


                System.out.print("Số giờ - nhập 'k' để bỏ qua: ");
                while(true){
                    try{
                        sGio1 = scanner.nextLine();
                        if(sGio1.equals("k")){
                            sGio = hoaDonList.get(i).getsGio();
                        }else{
                            sGio = Integer.parseInt(sGio1);
                        }
                        break;
                    }catch(NumberFormatException ex){
                        System.out.print("Lỗi định dạng, vui lòng nhập lại: ");
                    }
                }
                






                hd = new HoaDon(mhd, sPhong, lPhong, sGio, mnv);


                hoaDonList.set(i,hd);

                break;
            }
        }
        if(!isExisted) {
            System.out.println("Mã hóa đơn không hợp lệ!");
        }else {
            hoaDonDAO.write(hoaDonList);
        }
    }

    public void Delete(String mhd) {
        HoaDon hd = null;
        int size = hoaDonList.size();
        for (int i = 0; i < size; i++) {
            if(hoaDonList.get(i).getMhd().equals(mhd)) {
                hd = hoaDonList.get(i);
                break;
            }
        }
        if(hd != null) {
            hoaDonList.remove(hd);
            hoaDonDAO.write(hoaDonList);
        }
        else {
            System.out.println("Mã hóa đơn không hợp lệ!");
        }
    }
    
    public int nhapSo(){
        int chucvu;
        while(true){
            try{
                chucvu = Integer.parseInt(scanner.nextLine());
                return chucvu;
            }catch(NumberFormatException ex){
                System.out.print("Lỗi định dạng, vui lòng nhập lại: ");
            }
        }
    }

    public void SortHoaDon() {
        List<HoaDon> listSort = new ArrayList<>(hoaDonList);
        for(int i = 0 ; i < listSort.size() ; i ++){
            for( int j = listSort.size() - 1 ; j > i ; j --){
                if(listSort.get(j).getThanhTien() > listSort.get(i).getThanhTien()){
                    HoaDon tmp = listSort.get(j);
                    listSort.set(j, listSort.get(i));
                    listSort.set(i, tmp);

                }
            }
        }

        // Ghi danh sach da sap xep vao file
        hoaDonDAO.write(listSort, "SortHoaDon.txt");

        System.out.println("______________________Danh sách hóa đơn______________________");
        System.out.format("%20s %20s %20s %20s %20s %20s \n", "Mã hóa đơn", "Nhân viên lập", "Số phòng", "Loại phòng", "Số giờ", "Thành tiền");
        for (HoaDon hoaDon : listSort) {
            hoaDon.Show();
        }
    }

    public void FindMHD(String mhd){
        System.out.println("______________________Danh sách tìm kiếm hóa đơn______________________");
        System.out.format("%20s %20s %20s %20s %20s %20s \n", "Mã hóa đơn", "Nhân viên lập", "Số phòng", "Loại phòng", "Số giờ", "Thành tiền");

        List<HoaDon> listFind = new ArrayList<>(hoaDonList);
        for(int i = 0 ; i < listFind.size() ; i ++){
            if(listFind.get(i).getMhd().contains(mhd)){
                listFind.get(i).Show();
            }
        }
    }

    public void show() {
        System.out.println("______________________Danh sách hóa đơn______________________");
        System.out.format("%20s %20s %20s %20s %20s %20s \n", "Mã hóa đơn", "Nhân viên lập", "Số phòng", "Loại phòng", "Số giờ", "Thành tiền");
        for (HoaDon hd : hoaDonList) {
            hd.Show();

        }
    }
}

