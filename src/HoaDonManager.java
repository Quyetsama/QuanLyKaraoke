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
        System.out.print("Số phòng: ");
        int sPhong = scanner.nextInt();

        System.out.print("Loại Phòng (0: Thường 50K/h, 1: VIP 70K/h): ");
        int lPhong = scanner.nextInt();

        System.out.print("Số giờ: ");
        int sGio = scanner.nextInt();

        HoaDon hd = new HoaDon(mhd, sPhong, lPhong, sGio);
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

                String sPhong, lPhong, sGio;

                System.out.print("Số phòng - nhập 'k' để bỏ qua: "); sPhong = scanner.next();
                if(sPhong.equals("k")){
                    sPhong = String.valueOf(hoaDonList.get(i).getsPhong());
                }

                System.out.print("Loại phòng - nhập 'k' để bỏ qua: "); lPhong = scanner.next();
                if(lPhong.equals("k")){
                    lPhong = String.valueOf(hoaDonList.get(i).getLphong());
                }


                System.out.print("Số giờ - nhập 'k' để bỏ qua: "); sGio = scanner.next();
                if(sGio.equals("k")){
                    sGio = String.valueOf(hoaDonList.get(i).getsGio());
                }




                hd = new HoaDon(mhd, Integer.parseInt(sPhong), Integer.parseInt(lPhong), Integer.parseInt(sGio));


                hoaDonList.set(i,hd);

                break;
            }
        }
        if(!isExisted) {
            System.out.println("Mã hóa đơn không hợp lê!");
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
            System.out.println("Mã hóa đơn không hợp lê!");
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

        // Ghi danh sách đã sắp xếp vào file
        hoaDonDAO.write(listSort, "SortHoaDon.txt");

        System.out.println("______________________Danh sách hóa đơn______________________");
        System.out.format("%20s %20s %20s %20s %20s \n", "Mã hóa đơn", "Số phòng", "Loại phòng", "Số giờ", "Thành tiền");
        for (HoaDon hoaDon : listSort) {
            hoaDon.Show();
        }
    }

    public void FindMHD(String mhd){
        System.out.println("______________________Danh sách tìm kiếm hóa đơn______________________");
        System.out.format("%20s %20s %20s %20s %20s \n", "Mã hóa đơn", "Số phòng", "Loại phòng", "Số giờ", "Thành tiền");

        List<HoaDon> listFind = new ArrayList<>(hoaDonList);
        for(int i = 0 ; i < listFind.size() ; i ++){
            if(listFind.get(i).getMhd().contains(mhd)){
                listFind.get(i).Show();
            }
        }
    }


    public void show() {
        System.out.println("______________________Danh sách hóa đơn______________________");
        System.out.format("%20s %20s %20s %20s %20s \n", "Mã hóa đơn", "Số phòng", "Loại phòng", "Số giờ", "Thành tiền");
        for (HoaDon hd : hoaDonList) {
            hd.Show();

        }
    }
}