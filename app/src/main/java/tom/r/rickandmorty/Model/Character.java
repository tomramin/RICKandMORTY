package tom.r.rickandmorty.Model;

public class Character {

    private String name;
    private String status;
    private String species;

    private String gender;
    private String image;

    public Character(String name, String status, String species, String gender, String image) {
        this.name = name;
        this.status = status;
        this.species = species;
        this.gender = gender;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getGender() {
        return gender;
    }

    public String getImage() {
        return image;
    }

}
