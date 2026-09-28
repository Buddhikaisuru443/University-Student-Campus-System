public class ServiceRequest {

    private String studentId;
    private String requestType;

    public ServiceRequest(String studentId, String requestType) {
        this.studentId = studentId;
        this.requestType = requestType;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getRequestType() {
        return requestType;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId
                + " | Request: " + requestType;
    }
}