package crm_app10.entity;

public class Status {
    private int id;
    private String name;
    
    // Constructors
    public Status() {
    }
    
    public Status(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
