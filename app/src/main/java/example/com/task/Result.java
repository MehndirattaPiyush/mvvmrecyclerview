package example.com.task;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("avatar")
    @Expose
    private String avatar;






    /**
     *
     * @return
     * The overview
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The overview
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     * The
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The release_date
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The originalTitle
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     *
     * @param avatar
     * The original_title
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}
