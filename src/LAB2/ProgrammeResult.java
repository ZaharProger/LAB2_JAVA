package LAB2;
//Результат работы программы/подпрограммы
public class ProgrammeResult{
    private String message;
    private boolean isSuccessful;

    public ProgrammeResult(){
        message = "Не были получены входные данные!";
        isSuccessful = false;
    }

    public ProgrammeResult(String message, boolean isSuccessful){
        this.message = message;
        this.isSuccessful = isSuccessful;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setSuccessStatus(boolean status){
        isSuccessful = status;
    }

    public String getMessage(){
        return message;
    }

    public boolean getSuccessStatus(){
        return isSuccessful;
    }
}
