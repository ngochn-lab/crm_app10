package entity;

public class Tasks {
    private int id;
    private String name;
    private String startDate;
    private String endDate;
    private int userId;
    private int projectId;
    private int statusId;
    
    // Additional fields for joined data
    private String userName;
    private String userFullname;
    private String projectName;
    private String statusName;
    
    // Constructors
    public Tasks() {
    }
    
    public Tasks(int id, String name, String startDate, String endDate, 
                 int userId, int projectId, int statusId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
        this.projectId = projectId;
        this.statusId = statusId;
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
    
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getProjectId() {
        return projectId;
    }
    
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    public int getStatusId() {
        return statusId;
    }
    
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserFullname() {
        return userFullname;
    }
    
    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }
    
    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public String getStatusName() {
        return statusName;
    }
    
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    
    @Override
    public String toString() {
        return "Tasks{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", userId=" + userId +
                ", projectId=" + projectId +
                ", statusId=" + statusId +
                ", userName='" + userName + '\'' +
                ", userFullname='" + userFullname + '\'' +
                ", projectName='" + projectName + '\'' +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
