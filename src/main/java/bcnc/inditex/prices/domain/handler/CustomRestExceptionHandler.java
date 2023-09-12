package bcnc.inditex.prices.domain.handler;

import bcnc.inditex.prices.domain.dto.ApiErrorDto;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        String error = ex.getLocalizedMessage();
        FieldError fe = ex.getBindingResult().getFieldError();
        String msg = fe.getField() + ": " + fe.getDefaultMessage();
        ApiErrorDto e_res = new ApiErrorDto(HttpStatus.BAD_REQUEST, msg, error);
        return handleExceptionInternal(ex, e_res, headers, e_res.getStatus(), request);
    }


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        String msg = ex.getParameterName() + " parameter is missing";
        String errors = ex.getLocalizedMessage();
        ApiErrorDto e_res = new ApiErrorDto(HttpStatus.BAD_REQUEST, msg, errors);
        return new ResponseEntity<Object>(e_res, new HttpHeaders(), e_res.getStatus());
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest requespt) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }
        String msg = errors.toString();
        String error = ex.getLocalizedMessage();
        ApiErrorDto e_res = new ApiErrorDto(HttpStatus.BAD_REQUEST, msg, error);
        return new ResponseEntity<Object>(e_res, new HttpHeaders(), e_res.getStatus());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String msg = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        String error = ex.getLocalizedMessage();
        ApiErrorDto e_res = new ApiErrorDto(HttpStatus.BAD_REQUEST, msg, error);
        return new ResponseEntity<Object>(
                e_res, new HttpHeaders(), e_res.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String msg = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        String error = ex.getLocalizedMessage();
        ApiErrorDto e_res = new ApiErrorDto(HttpStatus.NOT_FOUND, msg, error);
        return new ResponseEntity<Object>(e_res, new HttpHeaders(), e_res.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
        String msg = builder.toString();
        String error = ex.getLocalizedMessage();
        ApiErrorDto e_res = new ApiErrorDto(HttpStatus.METHOD_NOT_ALLOWED, msg, error);
        return new ResponseEntity<Object>(e_res, new HttpHeaders(), e_res.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));
        String error = builder.substring(0, builder.length() - 2);
        String msg = ex.getLocalizedMessage();
        ApiErrorDto e_res = new ApiErrorDto(HttpStatus.UNSUPPORTED_MEDIA_TYPE, msg, error);
        return new ResponseEntity<Object>(e_res, new HttpHeaders(), e_res.getStatus());
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ApiErrorDto e_res;
        Throwable cause = ex.getCause();
        if (cause == null) {
            e_res = new ApiErrorDto(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), null);
        } else if (cause instanceof InvalidFormatException) {
            InvalidFormatException ife = (InvalidFormatException) ex.getCause();
            String error = ife.getLocalizedMessage();
            String msg = "The value " +  ife.getValue() + " does not match the expected format";
            e_res = new ApiErrorDto(HttpStatus.UNPROCESSABLE_ENTITY, msg, error);
        } else {
            e_res = new ApiErrorDto(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), null);
        }
        return new ResponseEntity<Object>(e_res, new HttpHeaders(), e_res.getStatus());
    }



    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        String msg = ex.getLocalizedMessage();
        String error = ex.getCause().toString();
        ApiErrorDto e_res = new ApiErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, msg, error);
        return new ResponseEntity<Object>(e_res, new HttpHeaders(), e_res.getStatus());
    }
}
