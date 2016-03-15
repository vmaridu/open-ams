package org.openams.rest.jpa.entity;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.openams.rest.jpa.entity.enums.UserStatus;


/**
 * The persistent class for the user database table.
 *
 */
@ApiModel(description = "User Account Data; All Time Stamps shoud be passed as  GMT Epoch Milli Seconds")
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "User Name", required = true, dataType = "String")
    @NotNull
    @Size(min = 1, max=255)
    @Id
    @Column(name="user_name")
    private String userName;

    @ApiModelProperty(value = "Account Expire Date Time in GMT Epoch Milli Seconds; Type : long", dataType = "long")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="account_expire_dtt")
    private Date accountExpireDtt;

    @ApiModelProperty(value = "Credentials Expire Date Time in GMT Epoch Milli Seconds; Type : long", dataType = "long")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="credentials_expire_dtt")
    private Date credentialsExpireDtt;

    @ApiModelProperty(value = "E Mail", dataType = "String")
    @Column(name="e_mail")
    private String eMail;

    @ApiModelProperty(value = "Last Access Date Time in GMT Epoch Milli Seconds; Type : long", dataType = "long")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_access_dtt")
    private Date lastAccessDtt;

    @ApiModelProperty(value = "Password", required = true, dataType = "String")
    @NotNull
    @Size(min = 8, max=500)
    private String password;

    @Column(name="password_salt")
    private String passwordSalt;

    @ApiModelProperty(value = "User Account Status", required = true, dataType = "UserStatus")
    @Enumerated(EnumType.ORDINAL)
    private UserStatus status;


    //persist or edit on user doesn't cause creation of new roles
    @ApiModelProperty(value = "User Roles", required = true, dataType = "Role")
    @Enumerated(EnumType.ORDINAL)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_in_role"
            , joinColumns={
                    @JoinColumn(name="user_name")
            }
            , inverseJoinColumns={
                    @JoinColumn(name="role_id")
            }
            )
    private List<Role> roles;

    public User() {
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getAccountExpireDtt() {
        return this.accountExpireDtt;
    }

    public void setAccountExpireDtt(Date accountExpireDtt) {
        this.accountExpireDtt = accountExpireDtt;
    }

    public Date getCredentialsExpireDtt() {
        return this.credentialsExpireDtt;
    }

    public void setCredentialsExpireDtt(Date credentialsExpireDtt) {
        this.credentialsExpireDtt = credentialsExpireDtt;
    }

    public String getEMail() {
        return this.eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public Date getLastAccessDtt() {
        return this.lastAccessDtt;
    }

    public void setLastAccessDtt(Date lastAccessDtt) {
        this.lastAccessDtt = lastAccessDtt;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return this.passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public UserStatus getStatus() {
        return this.status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}