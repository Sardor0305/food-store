package uz.example.foodstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T>{
    private boolean success;
    private T data;
    private String message;


    public Response(T data){
        this.data=data;
        this.success=true;
    }
    public Response(Boolean success,String message){
        this.success=success;
        this.message=message;
    }

}
