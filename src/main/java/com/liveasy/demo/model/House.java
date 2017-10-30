package com.liveasy.demo.model;

import com.liveasy.demo.model.HouseSubmodels.Comment;
import com.liveasy.demo.model.HouseSubmodels.Layout;
import com.liveasy.demo.model.HouseSubmodels.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    /*
     * Originally, many properties like Address, bedroom and washroom are stored as properties in House.class
     * However, it is much more scalable in the future and painless to do the type migration now.
     * It also works better with Google Geo Api JSON and RestTemplate
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @OneToOne(cascade = CascadeType.ALL)
    private Layout layout;

    @OneToOne(cascade = CascadeType.ALL)
    private Comment comment;


    @Lob
    private Byte[] image;

    public String getFullAddress(){
        return location.getFullLocation();
    }

    /*
     * Why Don't we keep using Project for Setters?:
     *      Because we want to keep a bidirectional mapping from House to its subclasses.
     *      So, it is wise to setup setters on both side so, when a layout is assigned to a house, the house also tells
     *      its new layout that it owns the layout now -> layout.setUser(this)
     */

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLocation(Location location) {
        this.location = location;
        location.setHouse(this);
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
        layout.setHouse(this);
    }

    public void setComment(Comment comment) {
        this.comment = comment;
        comment.setHouse(this);
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }
}
