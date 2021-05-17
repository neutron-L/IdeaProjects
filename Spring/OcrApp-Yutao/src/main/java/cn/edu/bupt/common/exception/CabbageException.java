package cn.edu.bupt.common.exception;

import cn.edu.bupt.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CabbageException extends RuntimeException {
    private ExceptionEnum exceptionEnum;

}