import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Karaoke {
    public static void main(String[] args) {
//        NhanVien nv1, nv2, nv3 = new NhanVien();
//        nv1 = new QuanLyNhanVien.NhanVienFullTime("NV001", "Nguyễn Văn Quyết1", "28/08/2001", "Nam");
//        nv1.setChucVu(1);
//
//        nv2 = new QuanLyNhanVien.NhanVienFullTime("NV002", "Nguyễn Văn Quyết2", "28/08/2001", "Nam", 10);
//
//        nv3 = new QuanLyNhanVien.NhanVienPartTime("NV003", "Nguyễn Văn Quyết3", "28/08/2001", "Nam", 150);
//
////        nv1.TinhLuong();
////        nv2.TinhLuong();
////        nv3.TinhLuong();
//
//
//        nv1.Show();
//        nv2.Show();
//        nv3.Show();


//        NhanVienManager manager = new NhanVienManager();
//
//        manager.Add();
//        manager.show();

//        NhanVienManager manager = new NhanVienManager();
//
//        manager.Delete("1");
//        manager.show();


        int x = 0;
        Scanner sc = new Scanner(System.in);
        boolean cont1 = true;
        do{
            System.out.println("___________________MENU___________________");
            System.out.println("1, Quản lý nhân viên");
            System.out.println("2, Quản lý sản phẩm");
            System.out.println("3, Quản lý nhà cung cấp");
            System.out.println("4, Quản lý hóa đơn");
            System.out.println("5, Thoát");
            x = sc.nextInt();
            switch (x){
                case 1:
                    NhanVienManager manager = new NhanVienManager();

                    boolean cont2 = true;
                    do{
                        System.out.println("___________________QUẢN LÝ NHÂN VIÊN___________________");
                        System.out.println("1, Thêm");
                        System.out.println("2, Sửa");
                        System.out.println("3, Xóa");
                        System.out.println("4, Tìm kiếm");
                        System.out.println("5, Sắp xếp");
                        System.out.println("6, Hiển thị danh sách");
                        System.out.println("7, Quay lại");

                        int y = 0;
                        y = sc.nextInt();
                        switch (y){
                            case 1:
                                manager.Add();
                                break;
                            case 2:
                                System.out.print("Nhập mã nhân viên cần sửa: ");
                                manager.Edit(sc.next());
                                break;
                            case 3:
                                System.out.print("Nhập mã nhân viên cần xóa: ");
                                manager.Delete(sc.next());
                                break;
                            case 4:
                                System.out.println("1, Mã nhân viên");
                                System.out.println("2, Tên");

                                int z = 0;
                                z = sc.nextInt();

                                switch (z){
                                    case 1:
                                        System.out.print("Nhập mã nhân viên cần tìm: ");
                                        manager.FindMNV(sc.next());
                                        break;
                                    case 2:
                                        System.out.print("Nhập tên nhân viên cần tìm: ");
                                        manager.FindName(sc.next());
                                        break;
                                }

                                break;
                            case 5:
                                System.out.println("1, Tên");
                                System.out.println("2, Lương");

                                int t = 0;
                                t = sc.nextInt();

                                switch (t){
                                    case 1:
                                        manager.SortName();
                                        break;
                                    case 2:
                                        manager.SortSalary();
                                        break;
                                }

                                break;
                            case 6:
                                manager.show();
                                break;
                            case 7:
                                cont2= false;
                                break;
                        }
                    }while (cont2);
                    break;

                case 2:
                    SanPhamManager managerSP = new SanPhamManager();

                    boolean cont3 = true;
                    do{
                        System.out.println("___________________QUẢN LÝ SẢN PHẨM___________________");
                        System.out.println("1, Thêm");
                        System.out.println("2, Sửa");
                        System.out.println("3, Xóa");
                        System.out.println("4, Tìm kiếm");
                        System.out.println("5, Sắp xếp");
                        System.out.println("6, Hiển thị danh sách");
                        System.out.println("7, Quay lại");

                        int y = 0;
                        y = sc.nextInt();
                        switch (y){
                            case 1:
                                managerSP.Add();
                                break;
                            case 2:
                                System.out.print("Nhập mã sản phẩm cần sửa: ");
                                managerSP.Edit(sc.next());
                                break;
                            case 3:
                                System.out.print("Nhập mã sản phẩm cần xóa: ");
                                managerSP.Delete(sc.next());
                                break;
                            case 4:
                                System.out.println("1, Mã sản phẩm");
                                System.out.println("2, Tên");

                                int z = 0;
                                z = sc.nextInt();

                                switch (z){
                                    case 1:
                                        System.out.print("Nhập mã sản phẩm cần tìm: ");
                                        managerSP.FindMSP(sc.next());
                                        break;
                                    case 2:
                                        System.out.print("Nhập tên sản phẩm cần tìm: ");
                                        managerSP.FindName(sc.next());
                                        break;
                                }
                                break;
                            case 5:
                                System.out.println("1, Tên");
                                System.out.println("2, Đơn giá");

                                int t = 0;
                                t = sc.nextInt();

                                switch (t){
                                    case 1:
                                        managerSP.SortSanPhamName();
                                        break;
                                    case 2:
                                        managerSP.SortSanPhamPrice();
                                        break;
                                }
                                break;
                            case 6:
                                managerSP.show();
                                break;
                            case 7:
                                cont3= false;
                                break;
                        }
                    }while (cont3);
                    break;
                case 3:

                    break;

                case 4:

                    HoaDonManager managerHD = new HoaDonManager();

                    boolean cont5 = true;
                    do{
                        System.out.println("___________________QUẢN LÝ HÓA ĐƠN___________________");
                        System.out.println("1, Thêm");
                        System.out.println("2, Sửa");
                        System.out.println("3, Xóa");
                        System.out.println("4, Tìm kiếm");
                        System.out.println("5, Sắp xếp (Thành tiền)");
                        System.out.println("6, Hiển thị danh sách");
                        System.out.println("7, Quay lại");

                        int y = 0;
                        y = sc.nextInt();
                        switch (y){
                            case 1:
                                managerHD.Add();
                                break;
                            case 2:
                                System.out.print("Nhập mã hóa đơn cần sửa: ");
                                managerHD.Edit(sc.next());
                                break;
                            case 3:
                                System.out.print("Nhập mã hóa đơn cần xóa: ");
                                managerHD.Delete(sc.next());
                                break;
                            case 4:
                                System.out.print("Nhập mã hóa đơn cần tìm: ");
                                managerHD.FindMHD(sc.next());
                                break;
                            case 5:
                                managerHD.SortHoaDon();
                                break;
                            case 6:
                                managerHD.show();
                                break;
                            case 7:
                                cont5= false;
                                break;
                        }
                    }while (cont5);

                    break;

                case 5:
                    cont1 = false;
                    break;
            }
        }while (cont1);

    }
}
