use DA_QLBANDIENTHOAI_IPHONE_NHOM3_


--- lấy thông tin sp của màn bán hàng        
	SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, 
	dbo.SanPham.So_Luong, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, dbo.MauSac.Mau_Sac, COUNT(SanPham.ID_San_Pham)as'SoLuongImei'
FROM         dbo.SanPham INNER JOIN
                      dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                      dbo.Imei ON dbo.SanPham.ID_San_Pham = dbo.Imei.ID_San_Pham
					  where Imei.Trang_Thai = 1
group by	 dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, 
dbo.SanPham.So_Luong, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, dbo.MauSac.Mau_Sac
-- tọa hóa đơn ở mà bán hàng 
SELECT    dbo.HoaDon.ID_Hoa_Don, dbo.HoaDon.Ma_Hoa_Don, CONVERT(date, dbo.HoaDon.Ngay_Tao) as'NgayTao', dbo.HoaDon.Tong_Gia, dbo.HoaDon.ID_Khach_Hang, dbo.HoaDon.ID_Nhan_Vien, dbo.HoaDon.Trang_Thai, dbo.NhanVien.Ma_NV, 
                      dbo.NhanVien.Ho_Ten, dbo.KhachHang.Ma_Khach_Hang, dbo.KhachHang.Ten
FROM         dbo.HoaDon INNER JOIN
                      dbo.NhanVien ON dbo.HoaDon.ID_Nhan_Vien = dbo.NhanVien.ID_Nhan_Vien left JOIN
                      dbo.KhachHang ON dbo.HoaDon.ID_Khach_Hang = dbo.KhachHang.ID_Khach_Hang
					  where HoaDon.Trang_Thai = 0
					  order by HoaDon.Ngay_Tao desc

 -- fill to table sản phẩm ở bảng sản phẩm -- TT_Imei = 1 -> chưa bán

SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.SanPham.Mo_Ta, dbo.SanPham.Ngay_Tao, dbo.SanPham.So_Luong, dbo.SanPham.Gia_Nhap, dbo.SanPham.Gia_Ban, 
                      dbo.SanPham.Hinh_Anh, dbo.SanPham.Trang_Thai, dbo.Rom.ID_Rom, dbo.Rom.Rom, dbo.XuatXu.ID_Xuat_Xu, dbo.XuatXu.Xuat_Xu, dbo.Ram.ID_Ram, dbo.Ram.Ram, dbo.PhanLoai.ID_Phan_Loai, 
                      dbo.PhanLoai.Phan_Loai, dbo.CPU.ID_CPU, dbo.CPU.CPU, dbo.DungLuongPin.ID_Dung_Luong_Pin, dbo.DungLuongPin.Dung_Luong_Pin, dbo.KichThuoc.ID_Kich_Thuoc, dbo.KichThuoc.Kich_Thuoc, 
                      dbo.MauSac.ID_Mau_Sac, dbo.MauSac.Mau_Sac,dbo.Imei.Trang_Thai AS 'TrangThai_Imei'
					  ,COUNT(SanPham.ID_San_Pham) as'SoLuongImei'
FROM         dbo.SanPham INNER JOIN
                      dbo.Rom ON dbo.SanPham.ID_Rom = dbo.Rom.ID_Rom INNER JOIN
                      dbo.XuatXu ON dbo.SanPham.ID_Xuat_Xu = dbo.XuatXu.ID_Xuat_Xu INNER JOIN
                      dbo.Ram ON dbo.SanPham.ID_Ram = dbo.Ram.ID_Ram INNER JOIN
                      dbo.PhanLoai ON dbo.SanPham.ID_Phan_Loai = dbo.PhanLoai.ID_Phan_Loai INNER JOIN
                      dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                      dbo.KichThuoc ON dbo.SanPham.ID_Kich_Thuoc = dbo.KichThuoc.ID_Kich_Thuoc INNER JOIN
                      dbo.DungLuongPin ON dbo.SanPham.ID_Dung_Luong_Pin = dbo.DungLuongPin.ID_Dung_Luong_Pin INNER JOIN
                      dbo.CPU ON dbo.SanPham.ID_CPU = dbo.CPU.ID_CPU INNER JOIN
                      dbo.Imei ON dbo.SanPham.ID_San_Pham = dbo.Imei.ID_San_Pham
GROUP BY dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.SanPham.Mo_Ta, dbo.SanPham.Ngay_Tao, dbo.SanPham.So_Luong, dbo.SanPham.Gia_Nhap, dbo.SanPham.Gia_Ban, 
                      dbo.SanPham.Hinh_Anh, dbo.SanPham.Trang_Thai, dbo.Rom.ID_Rom, dbo.Rom.Rom, dbo.XuatXu.ID_Xuat_Xu, dbo.XuatXu.Xuat_Xu, dbo.Ram.ID_Ram, dbo.Ram.Ram, dbo.PhanLoai.ID_Phan_Loai, 
                      dbo.PhanLoai.Phan_Loai, dbo.CPU.ID_CPU, dbo.CPU.CPU, dbo.DungLuongPin.ID_Dung_Luong_Pin, dbo.DungLuongPin.Dung_Luong_Pin, dbo.KichThuoc.ID_Kich_Thuoc, dbo.KichThuoc.Kich_Thuoc, 
                      dbo.MauSac.ID_Mau_Sac, dbo.MauSac.Mau_Sac, dbo.Imei.Trang_Thai

-- lấy ra thông tin của bảng sản phẩm 
SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.SanPham.Mo_Ta, CONVERT(date,dbo.SanPham.Ngay_Tao) as'NgayTao', dbo.SanPham.So_Luong, dbo.SanPham.Gia_Nhap, dbo.SanPham.Gia_Ban, 
                      dbo.SanPham.Hinh_Anh, dbo.SanPham.Trang_Thai, dbo.Rom.ID_Rom, dbo.Rom.Rom, dbo.MauSac.ID_Mau_Sac, dbo.MauSac.Mau_Sac, dbo.Ram.ID_Ram, dbo.Ram.Ram, dbo.KichThuoc.ID_Kich_Thuoc, 
                      dbo.KichThuoc.Kich_Thuoc, dbo.PhanLoai.ID_Phan_Loai, dbo.PhanLoai.Phan_Loai, dbo.DungLuongPin.ID_Dung_Luong_Pin, dbo.DungLuongPin.Dung_Luong_Pin, dbo.XuatXu.ID_Xuat_Xu, dbo.XuatXu.Xuat_Xu, 
                      dbo.CPU.ID_CPU, dbo.CPU.CPU
FROM         dbo.SanPham INNER JOIN
                      dbo.Rom ON dbo.SanPham.ID_Rom = dbo.Rom.ID_Rom INNER JOIN
                      dbo.Ram ON dbo.SanPham.ID_Ram = dbo.Ram.ID_Ram INNER JOIN
                      dbo.PhanLoai ON dbo.SanPham.ID_Phan_Loai = dbo.PhanLoai.ID_Phan_Loai INNER JOIN
                      dbo.XuatXu ON dbo.SanPham.ID_Xuat_Xu = dbo.XuatXu.ID_Xuat_Xu INNER JOIN
                      dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                      dbo.KichThuoc ON dbo.SanPham.ID_Kich_Thuoc = dbo.KichThuoc.ID_Kich_Thuoc INNER JOIN
                      dbo.DungLuongPin ON dbo.SanPham.ID_Dung_Luong_Pin = dbo.DungLuongPin.ID_Dung_Luong_Pin INNER JOIN
                      dbo.CPU ON dbo.SanPham.ID_CPU = dbo.CPU.ID_CPU
WHERE SanPham.Trang_Thai = 0
order by SanPham.Ma_San_Pham desc

--- lấy ra thông tin của bảng  sản phẩm Imei
SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.SanPham.So_Luong, dbo.SanPham.Gia_Nhap, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, 
                      dbo.PhanLoai.ID_Phan_Loai, dbo.PhanLoai.Phan_Loai
					  , COUNT(SanPham.ID_San_Pham) as'SoLuongTon'
FROM         dbo.SanPham INNER JOIN
                      dbo.PhanLoai ON dbo.SanPham.ID_Phan_Loai = dbo.PhanLoai.ID_Phan_Loai INNER JOIN
                      dbo.Imei ON dbo.SanPham.ID_San_Pham = dbo.Imei.ID_San_Pham
					  where SanPham.Trang_Thai = 0 and Imei.Trang_Thai = 1
GROUP BY   dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.SanPham.So_Luong, dbo.SanPham.Gia_Nhap, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, 
                      dbo.PhanLoai.ID_Phan_Loai, dbo.PhanLoai.Phan_Loai 
ORDER BY dbo.SanPham.Ma_San_Pham desc
-- lấy ra imei khi click vào imei sản phẩm	
SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.Imei.ID_Imei, dbo.Imei.Ma_Imei, dbo.Imei.Trang_Thai
                    FROM         dbo.SanPham INNER JOIN
                                          dbo.Imei ON dbo.SanPham.ID_San_Pham = dbo.Imei.ID_San_Pham
                    where  Imei.Trang_Thai = 1 and SanPham.Ma_San_Pham = 'SP0001'
                    order by Imei.ID_Imei desc
-- lấy ra hóa đơn ch chi tiết khi click vào imei 
SELECT    dbo.HoaDon.Ma_Hoa_Don, dbo.HoaDonChiTiet.ID_HDCT, dbo.SanPham.ID_San_Pham, 
dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.HoaDonChiTiet.So_Luong, 
dbo.HoaDonChiTiet.Gia ,(count(dbo.SanPham.ID_San_Pham)*HoaDonChiTiet.Gia) as 'TongTien', 
count(dbo.SanPham.ID_San_Pham) as 'Số lượng Imei'
FROM         dbo.HoaDon INNER JOIN
                      dbo.HoaDonChiTiet ON dbo.HoaDon.ID_Hoa_Don = dbo.HoaDonChiTiet.ID_Hoa_Don INNER JOIN
                      dbo.ImeiDaBan ON dbo.HoaDonChiTiet.ID_HDCT = dbo.ImeiDaBan.ID_HDCT INNER JOIN
                      dbo.SanPham ON dbo.HoaDonChiTiet.ID_San_Pham = dbo.SanPham.ID_San_Pham
where HoaDon.Trang_Thai = 0 and HoaDon.Ma_Hoa_Don = 'HD0005'
GROUP BY dbo.HoaDon.Ma_Hoa_Don, dbo.HoaDonChiTiet.ID_HDCT, dbo.SanPham.ID_San_Pham, 
dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, dbo.HoaDonChiTiet.So_Luong, 
dbo.HoaDonChiTiet.Gia ,(HoaDonChiTiet.So_Luong*HoaDonChiTiet.Gia) 
order by dbo.HoaDonChiTiet.ID_HDCT desc

-- 
SELECT [ID_Giam_Gia]
      ,[Ma_Giam_Gia],[Ten_Chuong_Trinh],[Mo_Ta]
      ,[Ngay_Tao],[Ngay_Bat_Dau],[Ngay_Ket_Thuc]
      ,[So_luong],[Kieu_Giam],[Gia_tri_DH_Toi_Thieu]
      ,[Muc_Giam_Gia],[Muc_Giam_Gia_Toi_Da],[Trang_Thai],[ID_Nhan_Vien]
  FROM [dbo].[GiamGia]
  where Ngay_Bat_Dau <= GETDATE() and Ngay_Ket_Thuc >=GETDATE() and Gia_tri_DH_Toi_Thieu <= 300000
  --- tìm kiếm thông tin hóa đơn ở màn bán hàng 
   SELECT    dbo.HoaDon.ID_Hoa_Don, dbo.HoaDon.Ma_Hoa_Don, CONVERT(date, dbo.HoaDon.Ngay_Tao) as'NgayTao',
       dbo.HoaDon.Tong_Gia, dbo.HoaDon.ID_Khach_Hang, dbo.HoaDon.ID_Nhan_Vien, dbo.HoaDon.Trang_Thai, dbo.NhanVien.Ma_NV, 
        dbo.NhanVien.Ho_Ten, dbo.KhachHang.Ma_Khach_Hang, dbo.KhachHang.Ten ,dbo.KhachHang.SĐT
       FROM         dbo.HoaDon INNER JOIN
       dbo.NhanVien ON dbo.HoaDon.ID_Nhan_Vien = dbo.NhanVien.ID_Nhan_Vien left JOIN
       dbo.KhachHang ON dbo.HoaDon.ID_Khach_Hang = dbo.KhachHang.ID_Khach_Hang
                     where HoaDon.Trang_Thai = 0
					 and (
						HoaDon.Ma_Hoa_Don like 'HD0020'
						or HoaDon.Ngay_Tao like ''
						or NhanVien.Ma_NV like ''
						or NhanVien.Ho_Ten like ''
						or KhachHang.Ma_Khach_Hang like ''
						or KhachHang.Ten like ''
						or KhachHang.SĐT like ''
					 )
                     order by HoaDon.Ngay_Tao desc
-- tìm kiếm sản phẩm bán hang 
SELECT    dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, 
                 	dbo.SanPham.So_Luong, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, dbo.MauSac.Mau_Sac,
                     COUNT(SanPham.ID_San_Pham)as'SoLuongImei'
                 FROM         dbo.SanPham INNER JOIN
                                       dbo.MauSac ON dbo.SanPham.ID_Mau_Sac = dbo.MauSac.ID_Mau_Sac INNER JOIN
                                       dbo.Imei ON dbo.SanPham.ID_San_Pham = dbo.Imei.ID_San_Pham   
                 					  where Imei.Trang_Thai = 1 and dbo.SanPham.Trang_Thai = 0
									  and
									  (
										SanPham.Ma_San_Pham like 'SP0004' 
									or SanPham.Ten_San_Pham like ''
									or MauSac.Mau_Sac like ''
									
									  )
                 group by	 dbo.SanPham.ID_San_Pham, dbo.SanPham.Ma_San_Pham, dbo.SanPham.Ten_San_Pham, 
                 dbo.SanPham.So_Luong, dbo.SanPham.Gia_Ban, dbo.SanPham.Trang_Thai, dbo.MauSac.Mau_Sac
