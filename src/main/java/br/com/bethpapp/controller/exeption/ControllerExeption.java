package br.com.bethpapp.controller.exeption;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExeption extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		var campos = new ArrayList<Problema.Campo>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			campos.add(new Problema.Campo(nome, mensagem));
		}

		var problema = Problema.builder().
				status(status.value()).
				titulo("Um ou mais campos estão inválidos. " + "Faça o preenchimento correto e tente novamente").
				status(status.value()).
				dataHora(OffsetDateTime.now()).campos(campos).build();
                         
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
}
