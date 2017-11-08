package com.liveasy.demo.model;

import com.liveasy.demo.model.UserSubmodels.Preference;
import com.liveasy.demo.model.UserSubmodels.VerificationToken;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    /*
    The Id of the User. Generated at Sign-up;
    It is Generated by Strategy Identity. Non-Nullable, and acts as one of the hash variables.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    The Email of the User. Required at Sign-up.
     */
    //@NotEmpty(message = "* Please provide an Email!")
    //@Email(message = "* Please provide a valid Email!")
    private String email;
    private String username;

    /*
    The Password of the User. Required at Sign-up.
     */
    @Transient
    private String password;
    private String encryptedPassword;
    private Boolean enabled = true;


    private String firstName;
    private String lastName;
    private int active;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    private VerificationToken verificationToken;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    @OrderBy("id")
    private Set<House> houses = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    // ~ defaults to @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "user_id"),
    //     inverseJoinColumns = @joinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private Purpose purpose;

    @OneToOne(cascade = CascadeType.ALL)
    private Preference preference = new Preference();

    public User(){
        super();
        this.enabled = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if(this.id == null || user.getId()==null) return false;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername(){ return email; }

    public void setUsername(String username) {this.email = username; this.username = username;}

    public void setEmail(String email) {
        this.email = email;
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<House> getHouses() {
        return houses;
    }

    public void setHouses(Set<House> houses) {
        this.houses = houses;
    }

    public void addHouse(House house){
        house.setUser(this);
        houses.add(house);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        if(!this.roles.contains(role)){
            this.roles.add(role);
        }

        if(!role.getUsers().contains(this)){
            role.getUsers().add(this);
        }
    }

    public void removeRole(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);
    }


    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public VerificationToken getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(VerificationToken verificationToken) {
        this.verificationToken = verificationToken;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
        this.preference.setUser(this);
    }
}
