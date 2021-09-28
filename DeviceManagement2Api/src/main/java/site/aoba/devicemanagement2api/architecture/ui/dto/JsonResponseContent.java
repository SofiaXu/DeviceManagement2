package site.aoba.devicemanagement2api.architecture.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponseContent<T> implements Serializable {
    private int statusCode;
    private String message;
    private Date timestamp;
    private T content;
}
