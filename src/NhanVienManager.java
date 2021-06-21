import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NhanVienManager {
    public static Scanner scanner = new Scanner(System.in);
    private List<NhanVien> nhanVienList;
    private NhanVienDAO nhanVienDAO;

    public NhanVienManager(){
        nhanVienDAO = new NhanVienDAO();
        nhanVienList = nhanVienDAO.read();
    }

    public void Add(){
        int chucVu;
        String mnv, ht, ns, gt;
        long luong;


        System.out.print("Mã nhân viên: ");
        String[] s = null;
        if(nhanVienList.size() == 0){
            mnv = "NV1";
        }
        else{
            s = nhanVienList.get(nhanVienList.size()-1).getMnv().split("NV");
            mnv = "NV" + (Integer.parseInt(s[1]) + 1);
        }
        System.out.println(mnv);

        System.out.print("Chức vụ (1: Quản lý, 2: FullTime, 3: PartTime): "); chucVu = scanner.nextInt();




//        mnv = scanner.next();
        System.out.print("Họ tên: "); ht = scanner.next();
        scanner.nextLine();
        System.out.print("Ngày sinh: "); ns = scanner.next();
        System.out.print("Giới tính: "); gt = scanner.next();

        NhanVien nv;

        if(chucVu == 1){
            int lamThem;
            System.out.print("Số ngày làm thêm: "); lamThem = scanner.nextInt();

            if(lamThem > 0){
                nv = new NhanVienFullTime(mnv, ht, ns, gt, lamThem);
            }
            else {
                nv = new NhanVienFullTime(mnv, ht, ns, gt);
            }

            nv.setChucVu(1);
        }
        else if(chucVu == 2){
            int lamThem;
            System.out.print("Số ngày làm thêm: "); lamThem = scanner.nextInt();

            if(lamThem > 0){
                nv = new NhanVienFullTime(mnv, ht, ns, gt, lamThem);
            }
            else {
                nv = new NhanVienFullTime(mnv, ht, ns, gt);
            }
        }
        else {
            int gioLam;
            System.out.print("Số giờ làm việc: "); gioLam = scanner.nextInt();
            nv = new NhanVienPartTime(mnv, ht, ns, gt, gioLam);
        }

        nv.TinhLuong();

        nhanVienList.add(nv);
        nhanVienDAO.write(nhanVienList);
    }

    public void Edit(String mnv) {
        boolean isExisted = false;
        int size = nhanVienList.size();
        for (int i = 0; i < size; i++) {
            if (nhanVienList.get(i).getMnv().equals(mnv)) {
                isExisted = true;

                NhanVien nv;

                int chucVu;
                String chucVu1;
                String ht, ns, gt;
                int lamThem, lamViec;


                System.out.print("Chức vụ (1: Quản lý, 2: FullTime, 3: PartTime) - nhập 'k' để bỏ qua: "); chucVu1 = scanner.next();
                if(chucVu1.equals("k")){
                    chucVu = nhanVienList.get(i).getChucVu();
                }else {
                    chucVu = Integer.parseInt(chucVu1);
                }

                System.out.print("Họ tên - nhập 'k' để bỏ qua: "); ht = scanner.next();
                if(ht.equals("k")){
                    ht = nhanVienList.get(i).getHt();
                }

                System.out.print("Ngày sinh - nhập 'k' để bỏ qua: "); ns = scanner.next();
                if(ns.equals("k")){
                    ns = nhanVienList.get(i).getNs();
                }


                System.out.print("Giới tính - nhập 'k' để bỏ qua: "); gt = scanner.next();
                if(gt.equals("k")){
                    gt = nhanVienList.get(i).getGt();
                }




                if(chucVu == 1){
                    System.out.print("Số ngày làm thêm: "); lamThem = scanner.nextInt();

                    if(lamThem > 0){
                        nv = new NhanVienFullTime(mnv, ht, ns, gt, lamThem);
                    }
                    else {
                        nv = new NhanVienFullTime(mnv, ht, ns, gt);
                    }

                    nv.setChucVu(1);


                }
                else if(chucVu == 2){
                    System.out.print("Số ngày làm thêm: "); lamThem = scanner.nextInt();
                    if(lamThem > 0){
                        nv = new NhanVienFullTime(mnv, ht, ns, gt, lamThem);
                    }
                    else {
                        nv = new NhanVienFullTime(mnv, ht, ns, gt);
                    }


                }
                else {
                    System.out.print("Số giờ làm việc: "); lamViec = scanner.nextInt();

                    nv = new NhanVienPartTime(mnv, ht, ns, gt, lamViec);


                }

                nv.TinhLuong();
                nhanVienList.set(i, nv);

                break;
            }
        }
        if (!isExisted) {
            System.out.println("Mã nhân viên không hợp lệ!");
        } else {
            nhanVienDAO.write(nhanVienList);
        }
    }

    public void Delete(String mnv) {
        NhanVien nhanVien = null;
        int size = nhanVienList.size();
        for (int i = 0; i < size; i++) {
            if (nhanVienList.get(i).getMnv().equals(mnv)) {
                nhanVien = nhanVienList.get(i);
                break;
            }
        }
        if (nhanVien != null) {
            nhanVienList.remove(nhanVien);
            nhanVienDAO.write(nhanVienList);
        } else {
            System.out.println("Mã nhân viên không hợp lệ!");
        }
    }

    public void SortSalary(){
        List<NhanVien> listSort = new ArrayList<>(nhanVienList);
        for(int i = 0 ; i < listSort.size() ; i ++){
            for( int j = listSort.size() - 1 ; j > i ; j --){
                if(listSort.get(j).getLuong() > listSort.get(i).getLuong()){
                    NhanVien tmp = listSort.get(j);
                    listSort.set(j, listSort.get(i));
                    listSort.set(i, tmp);

                }
            }
        }

        // Ghi danh sách đã sắp xếp vào file
        nhanVienDAO.write(listSort, "sortSalary.txt");

        System.out.println("______________________Danh sách nhân viên______________________");
        System.out.format("%20s %20s %40s %20s %20s %20s \n", "Mã nhân viên", "Họ tên", "Chức vụ", "Ngày sinh", "Giới tính", "Lương");
        for (NhanVien nhanVien : listSort) {
            nhanVien.Show();
        }
    }

    public void SortName(){
        List<NhanVien> listSort = new ArrayList<>(nhanVienList);
        for(int i = 0 ; i < listSort.size() ; i ++){
            for( int j = listSort.size() - 1 ; j > i ; j --){
                if(listSort.get(j).getHt().compareTo(listSort.get(i).getHt()) < 0){
                    NhanVien tmp = listSort.get(j);
                    listSort.set(j, listSort.get(i));
                    listSort.set(i, tmp);

                }
            }
        }

        // Ghi danh sách đã sắp xếp vào file
        nhanVienDAO.write(listSort, "sortName.txt");

        System.out.println("______________________Danh sách nhân viên______________________");
        System.out.format("%20s %20s %40s %20s %20s %20s \n", "Mã nhân viên", "Họ tên", "Chức vụ", "Ngày sinh", "Giới tính", "Lương");
        for (NhanVien nhanVien : listSort) {
            nhanVien.Show();
        }
    }

    public void FindMNV(String mnv){
        System.out.println("______________________Danh sách tìm kiếm nhân viên______________________");
        System.out.format("%20s %20s %40s %20s %20s %20s \n", "Mã nhân viên", "Họ tên", "Chức vụ", "Ngày sinh", "Giới tính", "Lương");

        List<NhanVien> listFind = new ArrayList<>(nhanVienList);
        for(int i = 0 ; i < listFind.size() ; i ++){
            if(listFind.get(i).getMnv().contains(mnv)){
                listFind.get(i).Show();
            }
        }
    }

    public void FindName(String name){
        System.out.println("______________________Danh sách tìm kiếm nhân viên______________________");
        System.out.format("%20s %20s %40s %20s %20s %20s \n", "Mã nhân viên", "Họ tên", "Chức vụ", "Ngày sinh", "Giới tính", "Lương");

        List<NhanVien> listFind = new ArrayList<>(nhanVienList);
        for(int i = 0 ; i < listFind.size() ; i ++){
            if(listFind.get(i).getHt().contains(name)){
                listFind.get(i).Show();
            }
        }
    }

    public void show() {
        System.out.println("______________________Danh sách nhân viên______________________");
        System.out.format("%20s %20s %40s %20s %20s %20s \n", "Mã nhân viên", "Họ tên", "Chức vụ", "Ngày sinh", "Giới tính", "Lương");
        for (NhanVien nhanVien : nhanVienList) {
            nhanVien.Show();
        }
    }

    public List<NhanVien> getNhanVienList() {
        return nhanVienList;
    }
}
