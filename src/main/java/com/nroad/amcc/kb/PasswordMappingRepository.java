package com.nroad.amcc.kb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PasswordMappingRepository extends JpaRepository<PasswordMapping, String> {

    @Query(value = "select encrypted_string from kb_password_mapping where map_string=?1 ", nativeQuery = true)
    String findEncryptedStringByEightPassword(String eightPassword);
}
