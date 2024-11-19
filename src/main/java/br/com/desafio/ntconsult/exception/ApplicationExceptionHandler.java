package br.com.desafio.ntconsult.exception;

import br.com.desafio.ntconsult.models.dto.view.ErrorView;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler({GlobalException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleGlobalException(
            GlobalException exception,
            WebRequest request) {
        ErrorView error = ErrorView.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.name())
                .messages(Collections.singletonList(exception.getMessage())).build();

        return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @jakarta.validation.constraints.NotNull HttpHeaders headers,
            @jakarta.validation.constraints.NotNull HttpStatusCode status,
            @NotNull WebRequest request) {

        ErrorView error = criarListaDeErros(ex.getBindingResult());

        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }

    private ErrorView criarListaDeErros(BindingResult bindingResult){
        return ErrorView.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .messages(bindingResult.getFieldErrors().stream().map(error -> messageSource.getMessage(error, LocaleContextHolder.getLocale())).collect(Collectors.toList()))
                .error(HttpStatus.BAD_REQUEST.name()).build();
    }
}
