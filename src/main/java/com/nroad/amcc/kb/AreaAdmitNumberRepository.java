package com.nroad.amcc.kb;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Component;

        import java.util.List;

@Component
public interface AreaAdmitNumberRepository extends JpaRepository<AreaAdmitNumber, String> {

    @Query(value = "select * from kb_area_admit_number where area_name like %?1% and pr_code in(?2) and tenant_id=?3 order by admit_number desc limit 3", nativeQuery = true)
    List<AreaAdmitNumber> findAllByName(String areaName, List<String> prCodes,String tenantId);

    @Query(value = "select admit_number from kb_area_admit_number where  pr_code=?1 and tenant_id=?2 ", nativeQuery = true)
    int findNumberByPrCode(String prCode,String tenantId);
}
