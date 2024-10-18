package com.example.userCrud.Service;

import com.example.userCrud.Dto.EmployeeRes;
import com.example.userCrud.Dto.EmployeeReq;
import com.example.userCrud.Dto.UpdateEmployeeReq;
import com.example.userCrud.Entity.*;
import com.example.userCrud.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;

import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    private RiwayatJabatanRepository riwayatJabatanRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private SubDivisionRepository subDivisionRepository;

    @Autowired
    private JabatanRepository jabatanRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ValidationService validationService;

    // Method to generate employee number (NIK)
    public long generateEmployeeNumber(LocalDate joinDate) {
        // Ambil 2 digit terakhir dari tahun
        String year = String.valueOf(joinDate.getYear()).substring(2);

        String month = String.format("%02d", joinDate.getMonthValue());
        System.out.println("Bulan yang digunakan: " + month);

        // Hitung jumlah karyawan yang sudah mendaftar pada bulan tersebut
        LocalDate startOfMonth = joinDate.withDayOfMonth(1);
        LocalDate endOfMonth = joinDate.withDayOfMonth(joinDate.lengthOfMonth());
        long employeeCount = employeeRepository.countByJoinDateBetween(startOfMonth, endOfMonth) + 1;
        System.out.println("Employee count for " + joinDate.getMonth() + " " + joinDate.getYear() + ": " + employeeCount);

        // Format urutan karyawan menjadi 2 digit
        String employeeOrder = String.format("%02d", employeeCount);

        // Gabungkan tahun, bulan, dan urutan untuk menghasilkan NIK
        String employeeNumberString = year + month + employeeOrder;

        // Debugging: Lihat bagaimana hasil pembentukan NIK
        System.out.println("Generated NIK: " + employeeNumberString);

        // Konversikan NIK menjadi Long
        return Long.parseLong(employeeNumberString);
    }


    @Transactional
    public EmployeeRes create(EmployeeReq request) {
        validationService.validate(request);

        EmployeeEntity employee = new EmployeeEntity();
        employee.setJoinDate(LocalDate.now());  // Assuming joinDate is part of EmployeeReq

        // Generate NIK
        Long employeeNumber = generateEmployeeNumber(employee.getJoinDate());
        employee.setNIK(employeeNumber);

        employee.setName(request.getName());
        employee.setNo_ktp(request.getNo_ktp());
        employee.setNPWP(request.getNPWP());
        employee.setKartuKeluarga(request.getKartuKeluarga());
        employee.setJenisKelamin(request.getJenisKelamin());
        employee.setTempatLahir(request.getTempatLahir());
        employee.setAgama(request.getAgama());
        employee.setAlamatLengkap(request.getAlamatLengkap());
        employee.setAlamatDomisili(request.getAlamatDomisili());
        employee.setNoTelp(request.getNoTelp());
        employee.setKontakDarurat(request.getKontakDarurat());
        employee.setNoKontakDarurat(request.getNoKontakDarurat());
        employee.setEmailPribadi(request.getEmailPribadi());
        employee.setPendidikanTerakhir(request.getPendidikanTerakhir());
        employee.setJurusan(request.getJurusan());
        employee.setNamaUniversitas(request.getNamaUniversitas());
        employee.setNamaIbuKandung(request.getNamaIbuKandung());
        employee.setStatusPernikahan(request.getStatusPernikahan());
        employee.setJumlahAnak(request.getJumlahAnak());
        employee.setNomorRekening(request.getNomorRekening());
        employee.setBank(request.getBank());

        RiwayatJabatanEntity riwayatJabatan = new RiwayatJabatanEntity();

        riwayatJabatan.setStatusKontrak(request.getStatusKontrak());
        riwayatJabatan.setTmt_mulai(request.getTmt_awal());
        riwayatJabatan.setTmt_akhir(request.getTmt_akhir());
        riwayatJabatan.setKontrakKedua(request.getKontrakKedua());
        riwayatJabatan.setSalary(request.getSalary());
        riwayatJabatan.setAttachment(request.getAttachment());

        // Set DepartementEntity
        if (request.getDepartementId() != null) {
            DepartementEntity departementEntity = departementRepository.findById(request.getDepartementId())
                    .orElseThrow(() -> new EntityNotFoundException("Departement not found"));
            riwayatJabatan.setDepartementEntity(departementEntity);
        }

        // Set DivisionEntity
        if (request.getDivisionId() != null) {
            DivisionEntity divisionEntity = divisionRepository.findFirstById(request.getDivisionId())
                    .orElseThrow(() -> new EntityNotFoundException("Division not found"));
            riwayatJabatan.setDivisionEntity(divisionEntity);
        }

        // Set SubDivisionEntity
        if (request.getSubDivisionId() != null) {
            SubDivisionEntity subDivisionEntity = subDivisionRepository.findFirstById(request.getSubDivisionId())
                    .orElseThrow(() -> new EntityNotFoundException("SubDivision not found"));
            riwayatJabatan.setSubDivisionEntity(subDivisionEntity);
        }

        // Set JabatanEntities
        List<JabatanEntity> jabatanEntities = new ArrayList<>();
        for (Long jabatanId : request.getJabatanIds()) {
            JabatanEntity jabatanEntity = jabatanRepository.findById(jabatanId)
                    .orElseThrow(() -> new EntityNotFoundException("Jabatan not found"));
            jabatanEntities.add(jabatanEntity);
        }
        riwayatJabatan.setJabatanEntities(jabatanEntities);

        riwayatJabatanRepository.save(riwayatJabatan);

        employee.setRiwayatJabatan(riwayatJabatan);
        employeeRepository.save(employee);

        return toEmployeeResponse(employee);
    }

    @Transactional(readOnly = true)
    public EmployeeRes get(Long NIK) {
        EmployeeEntity employeeEntity = employeeRepository.findFirstByNIK(NIK)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));
        return toEmployeeResponse(employeeEntity);
    }

    @Transactional
    public EmployeeRes update(UpdateEmployeeReq request) {
        validationService.validate(request);

        EmployeeEntity employeeEntity = employeeRepository.findFirstByNIK(request.getNIK())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));

        RiwayatJabatanEntity riwayatJabatan = new RiwayatJabatanEntity();
        riwayatJabatan.setStatusKontrak(request.getStatusKontrak());
        riwayatJabatan.setTmt_mulai(request.getTmt_awal());
        riwayatJabatan.setTmt_akhir(request.getTmt_akhir());
        riwayatJabatan.setKontrakKedua(request.getKontrakKedua());
        riwayatJabatan.setSalary(request.getSalary());
        riwayatJabatan.setAttachment(request.getAttachment());

        riwayatJabatanRepository.save(riwayatJabatan);

        employeeEntity.setRiwayatJabatan(riwayatJabatan);

        employeeEntity.setName(request.getName());
        employeeEntity.setNo_ktp(request.getNo_ktp());
        employeeEntity.setNPWP(request.getNPWP());
        employeeEntity.setKartuKeluarga(request.getKartuKeluarga());
        employeeEntity.setJenisKelamin(request.getJenisKelamin());
        employeeEntity.setTempatLahir(request.getTempatLahir());
        employeeEntity.setAgama(request.getAgama());
        employeeEntity.setAlamatLengkap(request.getAlamatLengkap());
        employeeEntity.setAlamatDomisili(request.getAlamatDomisili());
        employeeEntity.setNoTelp(request.getNoTelp());
        employeeEntity.setKontakDarurat(request.getKontakDarurat());
        employeeEntity.setNoKontakDarurat(request.getNoKontakDarurat());
        employeeEntity.setEmailPribadi(request.getEmailPribadi());
        employeeEntity.setPendidikanTerakhir(request.getPendidikanTerakhir());
        employeeEntity.setJurusan(request.getJurusan());
        employeeEntity.setNamaUniversitas(request.getNamaUniversitas());
        employeeEntity.setNamaIbuKandung(request.getNamaIbuKandung());
        employeeEntity.setStatusPernikahan(request.getStatusPernikahan());
        employeeEntity.setJumlahAnak(request.getJumlahAnak());
        employeeEntity.setNomorRekening(request.getNomorRekening());
        employeeEntity.setBank(request.getBank());
        employeeEntity.setJoinDate(request.getJoinDate());

        employeeRepository.save(employeeEntity);

        return toEmployeeResponse(employeeEntity);
    }

    private EmployeeRes toEmployeeResponse(EmployeeEntity employeeEntity) {
        RiwayatJabatanEntity riwayatJabatan = employeeEntity.getRiwayatJabatan();
        return EmployeeRes.builder()
                .NIK(employeeEntity.getNIK())
                .name(employeeEntity.getName())
                .no_ktp(employeeEntity.getNo_ktp())
                .NPWP(employeeEntity.getNPWP())
                .kartuKeluarga(employeeEntity.getKartuKeluarga())
                .jenisKelamin(employeeEntity.getJenisKelamin())
                .tempatLahir(employeeEntity.getTempatLahir())
                .agama(employeeEntity.getAgama())
                .alamatLengkap(employeeEntity.getAlamatLengkap())
                .alamatDomisili(employeeEntity.getAlamatDomisili())
                .noTelp(employeeEntity.getNoTelp())
                .kontakDarurat(employeeEntity.getKontakDarurat())
                .noKontakDarurat(employeeEntity.getNoKontakDarurat())
                .emailPribadi(employeeEntity.getEmailPribadi())
                .pendidikanTerakhir(employeeEntity.getPendidikanTerakhir())
                .jurusan(employeeEntity.getJurusan())
                .namaUniversitas(employeeEntity.getNamaUniversitas())
                .namaIbuKandung(employeeEntity.getNamaIbuKandung())
                .statusPernikahan(employeeEntity.getStatusPernikahan())
                .jumlahAnak(employeeEntity.getJumlahAnak())
                .nomorRekening(employeeEntity.getNomorRekening())
                .bank(employeeEntity.getBank())
                .joinDate(employeeEntity.getJoinDate())
                .statusKontrak(riwayatJabatan.getStatusKontrak())
                .tmt_awal(riwayatJabatan.getTmt_mulai())
                .tmt_akhir(riwayatJabatan.getTmt_akhir())
                .kontrakKedua(riwayatJabatan.getKontrakKedua())
                .salary(riwayatJabatan.getSalary())
                .attachment(riwayatJabatan.getAttachment())
                .build();
    }
}
