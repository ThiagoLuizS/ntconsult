package br.com.desafio.ntconsult.exception;

import br.com.desafio.ntconsult.models.dto.view.ErrorView;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    private static final HttpStatus HTTP_STATUS_NOT_FOUND = HttpStatus.NOT_FOUND;
    private static final HttpStatus HTTP_STATUS_BAD_REQUEST = HttpStatus.BAD_REQUEST;


    @ExceptionHandler({GlobalException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleGlobalException(
            GlobalException ex,
            WebRequest request) {

        ErrorView error = criarListaDeErros(ex.getMessage(), HTTP_STATUS_NOT_FOUND);

        return handleExceptionInternal(ex, error, new HttpHeaders(), HTTP_STATUS_NOT_FOUND, request);
    }

    @ExceptionHandler({InvalidDataAccessApiUsageException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleInvalidDataAccessApiUsageException(
            InvalidDataAccessApiUsageException ex,
            WebRequest request) {

        ErrorView error = criarListaDeErros(ex.getMessage(), HTTP_STATUS_BAD_REQUEST);

        return handleExceptionInternal(ex, error, new HttpHeaders(), HTTP_STATUS_BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @jakarta.validation.constraints.NotNull HttpHeaders headers,
            @jakarta.validation.constraints.NotNull HttpStatusCode status,
            @NotNull WebRequest request) {

        ErrorView error = criarListaDeErros(ex.getBindingResult(), HTTP_STATUS_BAD_REQUEST);

        return handleExceptionInternal(ex, error, headers, HTTP_STATUS_BAD_REQUEST, request);
    }

    private ErrorView criarListaDeErros(BindingResult bindingResult, HttpStatus httpStatus){
        return ErrorView.builder()
                .status(httpStatus.value())
                .messages(bindingResult.getFieldErrors().stream().map(error -> messageSource.getMessage(error, LocaleContextHolder.getLocale())).collect(Collectors.toList()))
                .error(httpStatus.name()).build();
    }

    private ErrorView criarListaDeErros(String message, HttpStatus httpStatus){
        return ErrorView.builder()
                .status(httpStatus.value())
                .messages(List.of(message))
                .error(httpStatus.name()).build();
    }
}
