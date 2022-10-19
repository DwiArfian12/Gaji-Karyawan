-- phpMyAdmin SQL Dump
-- version 5.3.0-dev+20220522.7701cd71da
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 18 Okt 2022 pada 17.52
-- Versi server: 10.4.24-MariaDB
-- Versi PHP: 8.1.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_karyawan`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `t_gaji`
--

CREATE TABLE `t_gaji` (
  `nip` int(10) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `jabatan` varchar(255) NOT NULL,
  `gajipokok` int(10) NOT NULL,
  `transport` int(10) NOT NULL,
  `gajibersih` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `t_gaji`
--

INSERT INTO `t_gaji` (`nip`, `nama`, `jabatan`, `gajipokok`, `transport`, `gajibersih`) VALUES
(1222, 'Firmansyah Ateng', 'HRD', 4000000, 400000, 4400000),
(1234, 'Arfian', 'Direktur Utama', 8000000, 800000, 8800000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `t_gaji`
--
ALTER TABLE `t_gaji`
  ADD PRIMARY KEY (`nip`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



