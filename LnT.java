import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Kimberly Nauli Tobing
// NIM : 2702373021

//========================================================
public class LnT {
    private static List<Employee> employees = new ArrayList<>();

//========================================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Input Data Karyawan");
            System.out.println("2. View Data Karyawan");
            System.out.println("3. Update Data Karyawan");
            System.out.println("4. Delete Data Karyawan");
            System.out.println("5. Exit");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    inputDataKaryawan(scanner);
                    break;
                case 2:
                    viewDataKaryawan();
                    break;
                case 3:
                    updateDataKaryawan(scanner);
                    break;
                case 4:
                    deleteDataKaryawan(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        scanner.close();
    }
//========================================================
    public static void inputDataKaryawan(Scanner scanner) {
        System.out.println("Input data karyawan:");

        // Input nama karyawan
        String namaKaryawan = "";
        while (namaKaryawan.length() < 3) {
            System.out.print("Input nama karyawan [>= 3]: ");
            namaKaryawan = scanner.nextLine();
            if (namaKaryawan.length() < 3) {
                System.out.println("Nama karyawan harus memiliki panjang minimal 3 karakter.");
            }
        }

        // Input jenis kelamin
        String jenisKelamin = "";
        while (!jenisKelamin.equals("Laki-laki") && !jenisKelamin.equals("Perempuan")) {
            System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
            jenisKelamin = scanner.nextLine();
            if (!jenisKelamin.equals("Laki-laki") && !jenisKelamin.equals("Perempuan")) {
                System.out.println("Jenis kelamin harus Laki-laki atau Perempuan.");
            }
        }

        // Input jabatan
        String jabatan = "";
        while (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin")) {
            System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
            jabatan = scanner.nextLine();
            if (!jabatan.equals("Manager") && !jabatan.equals("Supervisor") && !jabatan.equals("Admin")) {
                System.out.println("Jabatan harus Manager, Supervisor, atau Admin.");
            }
        }

        // Proses input
        String idKaryawan = generateEmployeeID();
        System.out.println("Berhasil menambahkan karyawan dengan id " + idKaryawan);

        giveBonus(jabatan);

        // Simpan data karyawan ke dalam list
        Employee employee = new Employee(idKaryawan, namaKaryawan, jenisKelamin, jabatan);
        employees.add(employee);
    }

//========================================================
    public static void viewDataKaryawan() {
        if (employees.isEmpty()) {
            System.out.println("Belum ada data karyawan.");
            return;
        }

        Collections.sort(employees, Comparator.comparing(Employee::getNamaKaryawan));
        System.out.println("Data Karyawan:");
        System.out.println("Kode\tNama\tJenis Kelamin\tJabatan\tGaji");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public static void updateDataKaryawan(Scanner scanner) {
        if (employees.isEmpty()) {
            System.out.println("Belum ada data karyawan.");
            return;
        }

        viewDataKaryawan();
        System.out.print("Pilih nomor data yang ingin diupdate: ");
        int index = scanner.nextInt();
        scanner.nextLine(); 

        if (index >= 1 && index <= employees.size()) {
            Employee employeeToUpdate = employees.get(index - 1);

            System.out.println("Masukkan data baru (atau masukkan 0 untuk mempertahankan data lama):");
            inputDataKaryawan(scanner); 

            System.out.println("Data karyawan berhasil diupdate.");
        } else {
            System.out.println("Nomor data tidak valid.");
        }
    }

//========================================================
    public static void deleteDataKaryawan(Scanner scanner) {
        if (employees.isEmpty()) {
            System.out.println("Belum ada data karyawan.");
            return;
        }

        viewDataKaryawan();
        System.out.print("Pilih nomor data yang ingin dihapus: ");
        int index = scanner.nextInt();
        scanner.nextLine(); 

        if (index >= 1 && index <= employees.size()) {
            Employee deletedEmployee = employees.remove(index - 1);
            System.out.println("Data karyawan berhasil dihapus: " + deletedEmployee);
        } else {
            System.out.println("Nomor data tidak valid.");
        }
    }

//========================================================
    public static String generateEmployeeID() {
        return "AA-5512";
    }
//========================================================
    public static void giveBonus(String jabatan) {
        double bonusPercentage = 0;
        switch (jabatan) {
            case "Manager":
                bonusPercentage = 0.10;
                break;
            case "Supervisor":
                bonusPercentage = 0.075;
                break;
            case "Admin":
                bonusPercentage = 0.05;
                break;
        }
        System.out.println("Bonus sebesar " + (bonusPercentage * 100) + "% telah diberikan kepada karyawan dengan jabatan " + jabatan);
    }

//========================================================
    static class Employee {
        private String kodeKaryawan;
        private String namaKaryawan;
        private String jenisKelamin;
        private String jabatan;
//========================================================    
        public Employee(String kodeKaryawan, String namaKaryawan, String jenisKelamin, String jabatan) {
            this.kodeKaryawan = kodeKaryawan;
            this.namaKaryawan = namaKaryawan;
            this.jenisKelamin = jenisKelamin;
            this.jabatan = jabatan;
        }
 //========================================================   
        public String getNamaKaryawan() {
            return namaKaryawan;
        }
 //========================================================   
        public double calculateSalary(String jabatan) {
            double gaji = 0;
            switch (jabatan) {
                case "Manager":
                    gaji = 8_000_000;
                    break;
                case "Supervisor":
                    gaji = 6_000_000;
                    break;
                case "Admin":
                    gaji = 4_000_000;
                    break;
            }
            return gaji;
        }
//========================================================    
        @Override
        public String toString() {
            return kodeKaryawan + "\t" + namaKaryawan + "\t" + jenisKelamin + "\t" + jabatan + "\t" + calculateSalary(jabatan);
        }
    }
}
        
