package com.hellowd.server.api.repository;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-01-08
 * Time : 오후 2:36
 * AbstractPersistable 상의 ID를 seq로 변경
 */
@Entity
@Table(name="s3_store_owner")
public class StoreOwner {

    @Id
    @Column(name="seq",precision = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;


    @Column(name="seller_seq",precision = 20)
    private Long seller_seq;

    @Column(name="ver",length = 15)
    private String ver;

    @Column(name="id",length = 50)
    private String id;

    @Column(name="passwd",length = 40)
    private String passwd;

    @Column(name="name",length = 30)
    private String name;

    @Column(name="cid_watch",length = 500)
    private String cid_watch;

    @Column(name="cellnum",length = 15)
    private String cellnum;

    @Column(name="busi_no",length = 20)
    private String busi_no;

    @Column(name="transfer_type",nullable = false, columnDefinition = "INT(11) UNSIGNED ")
    private int transfer_type = 0;

    @Column(name="email",length = 50)
    private String email;

    @Column(name="bank_no",nullable = false,length = 50)
    private String bank_no;

    @Column(name="bank_name",nullable = false,length = 10)
    private String bank_name;

    @Column(name="bank_owner_name",nullable = false,length = 30)
    private String bank_owner_name;

    @Column(name="bank_code",length = 5)
    private String bank_code;

    @Column(name="card_no",length = 20)
    private String card_no;

    @Column(name="card_name",length = 10)
    private String card_name;

    @Column(name="card_valid_year",nullable = false,precision = 11)
    private int card_valid_year = 0;
    //update s3_store_owner set card_valid_year= 0 where card_valid_year is null;
    // alter table s3_store_owner modify card_valid_year int(11) not null default '0';

    @Column(name="card_valid_month",nullable = false,precision = 11)
    private int card_valid_month = 0;
    //Null value was assigned to a property of primitive type setter of com.hellowd.server.api.repository.StoreOwner.card_valid_month
    //TODO : 따라서 기존 값들을 모두 update 한다....
    //update s3_store_owner set card_valid_month= 0 where card_valid_month is null;
    // alter table s3_store_owner modify card_valid_month int(11) not null default '0';

    @Column(name="billing",nullable = false, columnDefinition = "INT(10) UNSIGNED ")
    private int billing = 0;

    @Column(name="transfer_date",nullable = false, columnDefinition = "INT(10) UNSIGNED ")
    private int transfer_date = 1;

    @Column(name="fixed_fee_non_pay_sum",nullable = false, columnDefinition = "INT(10) UNSIGNED ")
    private int fixed_fee_non_pay_sum = 0;

    @Column(name="settlement_non_pay_sum",nullable = false, columnDefinition = "INT(11) UNSIGNED ")
    private int settlement_non_pay_sum = 0;

    @Column(name="login_count", nullable = false, columnDefinition = "BIGINT(20) UNSIGNED ")
    private long login_count = 0 ;

    @Column(name="sign_up_path",precision = 10)
    private int sign_up_path;

    @Column(name="last_login_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_login_date;

    @Column(name="recommend", nullable = false ,precision = 10)
    private int recommend = 0;

    //2014.11.14 : 대표님 요청 : 페이나우 결제 취소시 login password 요청 여부 파라미터
    @Column(name="cancel_confirm",nullable = false, columnDefinition = " INT(10) UNSIGNED")
    private int cancel_confirm = 0;

    @Column(name="use_operation",nullable = false,precision = 10)
    private int use_operation = 0;

    @Column(name="birthdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthdate;

    @Column(name="register_waiting",nullable = false,precision = 11)
    private int register_waiting = 0;

    @Column(name="register_memo",length = 255)
    private String register_memo;

    @Column(name="regdate")
    private Date regdate ;

    @Column(name="mergeaddress",length = 1)
    private char mergeaddress;

    //Null value was assigned to a property of primitive type setter of com.hellowd.server.api.repository.StoreOwner.card_valid_month
    //TODO : 따라서 기존 값들을 모두 update 한다....
    //update s3_store_owner set mergeAddress= 0 where mergeAddress is null;
    // alter table s3_store_owner modify mergeAddress char(1) not null default '0';
    @Column(name="postcode",length = 7)
    private String postcode;

    @Column(name="zonecode",length = 50)
    private String zonecode;

    @Column(name="bcode",length = 50)
    private String bcode;

    @Column(name="bname",length = 10)
    private String bname;

    @Column(name="bldg_code",length = 50)
    private String bldg_code;

    @Column(name="bldg_name",length = 50)
    private String bldg_name;

    @Column(name="post_address",length = 100)
    private String post_address;

    @Column(name="legacy_post_address",length = 100)
    private String legacy_post_address;

    @Column(name="detail_post_address",length = 100)
    private String detail_post_address;

    @Column(name="gps_lat",length = 25)
    private String gps_lat;

    @Column(name="gps_lng",length = 25)
    private String gps_lng;

    public Long getSeller_seq() {
        return seller_seq;
    }

    public void setSeller_seq(Long seller_seq) {
        this.seller_seq = seller_seq;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid_watch() {
        return cid_watch;
    }

    public void setCid_watch(String cid_watch) {
        this.cid_watch = cid_watch;
    }

    public String getCellnum() {
        return cellnum;
    }

    public void setCellnum(String cellnum) {
        this.cellnum = cellnum;
    }

    public String getBusi_no() {
        return busi_no;
    }

    public void setBusi_no(String busi_no) {
        this.busi_no = busi_no;
    }

    public int getTransfer_type() {
        return transfer_type;
    }

    public void setTransfer_type(int transfer_type) {
        this.transfer_type = transfer_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBank_no() {
        return bank_no;
    }

    public void setBank_no(String bank_no) {
        this.bank_no = bank_no;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_owner_name() {
        return bank_owner_name;
    }

    public void setBank_owner_name(String bank_owner_name) {
        this.bank_owner_name = bank_owner_name;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public int getCard_valid_year() {
        return card_valid_year;
    }

    public void setCard_valid_year(int card_valid_year) {
        this.card_valid_year = card_valid_year;
    }

    public int getCard_valid_month() {
        return card_valid_month;
    }

    public void setCard_valid_month(int card_valid_month) {
        this.card_valid_month = card_valid_month;
    }

    public int getBilling() {
        return billing;
    }

    public void setBilling(int billing) {
        this.billing = billing;
    }

    public int getTransfer_date() {
        return transfer_date;
    }

    public void setTransfer_date(int transfer_date) {
        this.transfer_date = transfer_date;
    }

    public int getFixed_fee_non_pay_sum() {
        return fixed_fee_non_pay_sum;
    }

    public void setFixed_fee_non_pay_sum(int fixed_fee_non_pay_sum) {
        this.fixed_fee_non_pay_sum = fixed_fee_non_pay_sum;
    }

    public int getSettlement_non_pay_sum() {
        return settlement_non_pay_sum;
    }

    public void setSettlement_non_pay_sum(int settlement_non_pay_sum) {
        this.settlement_non_pay_sum = settlement_non_pay_sum;
    }

    public long getLogin_count() {
        return login_count;
    }

    public void setLogin_count(long login_count) {
        this.login_count = login_count;
    }

    public int getSign_up_path() {
        return sign_up_path;
    }

    public void setSign_up_path(int sign_up_path) {
        this.sign_up_path = sign_up_path;
    }

    public Date getLast_login_date() {
        return last_login_date;
    }

    public void setLast_login_date(Date last_login_date) {
        this.last_login_date = last_login_date;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public int getCancel_confirm() {
        return cancel_confirm;
    }

    public void setCancel_confirm(int cancel_confirm) {
        this.cancel_confirm = cancel_confirm;
    }

    public int getUse_operation() {
        return use_operation;
    }

    public void setUse_operation(int use_operation) {
        this.use_operation = use_operation;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getRegister_waiting() {
        return register_waiting;
    }

    public void setRegister_waiting(int register_waiting) {
        this.register_waiting = register_waiting;
    }

    public String getRegister_memo() {
        return register_memo;
    }

    public void setRegister_memo(String register_memo) {
        this.register_memo = register_memo;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public char getmergeaddress() {
        return mergeaddress;
    }

    public void setmergeaddress(char mergeaddress) {
        this.mergeaddress = mergeaddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getZonecode() {
        return zonecode;
    }

    public void setZonecode(String zonecode) {
        this.zonecode = zonecode;
    }

    public String getBcode() {
        return bcode;
    }

    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBldg_code() {
        return bldg_code;
    }

    public void setBldg_code(String bldg_code) {
        this.bldg_code = bldg_code;
    }

    public String getBldg_name() {
        return bldg_name;
    }

    public void setBldg_name(String bldg_name) {
        this.bldg_name = bldg_name;
    }

    public String getPost_address() {
        return post_address;
    }

    public void setPost_address(String post_address) {
        this.post_address = post_address;
    }

    public String getLegacy_post_address() {
        return legacy_post_address;
    }

    public void setLegacy_post_address(String legacy_post_address) {
        this.legacy_post_address = legacy_post_address;
    }

    public String getDetail_post_address() {
        return detail_post_address;
    }

    public void setDetail_post_address(String detail_post_address) {
        this.detail_post_address = detail_post_address;
    }

    public String getGps_lat() {
        return gps_lat;
    }

    public void setGps_lat(String gps_lat) {
        this.gps_lat = gps_lat;
    }

    public String getGps_lng() {
        return gps_lng;
    }

    public void setGps_lng(String gps_lng) {
        this.gps_lng = gps_lng;
    }

}
