package com.commerce.model.entity;

import com.commerce.model.domain.AccountStatus;
import com.commerce.model.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

  private String sellerName;

  private String mobile;

  @Column(unique = true,nullable = false)
  private  String email;
  private String password;

  @Embedded
  private BusinessDetails businessDetails =new BusinessDetails();
 @Embedded
  private BankDetails bankDetails = new BankDetails();
 @OneToOne(cascade = CascadeType.ALL)
 private Address pickupAddress =new Address();
    private String GSTIN;

    private USER_ROLE role=USER_ROLE.ROLE_SELLER;

    private boolean isEmailVerified=false;
    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;

}
