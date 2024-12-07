package com.poc.gerped.util;

import com.poc.gerped.DTO.Response.BaseResponseDTO;
import com.poc.gerped.DTO.Response.RetornoBaseDTO;
import com.poc.gerped.exception.ServicosException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class Utils {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <T> T converterParaTipo(Object origem, Class<T> destino) {
        return modelMapper.map(origem, destino);
    }


    public static ResponseEntity<BaseResponseDTO> getBaseResponseDTOResponseEntity(Integer statusHttp, String Mensagem, String numero, Object objeto, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(
                BaseResponseDTO.builder()
                        .dados(objeto)
                        .retorno(RetornoBaseDTO.builder()
                                .id(statusHttp)
                                .mensagem(Mensagem)
                                .numero(numero)
                                .build())
                        .build()
        );
    }

    public static ResponseEntity<BaseResponseDTO> tratarErroStatus400Status500(ServicosException e) {
        if (e.isErroNegocio()) {
            return Utils.getBaseResponseDTOResponseEntity(Constantes.HTTP_STATUS_400, e.getMessage(), Constantes.NUMERO_STATUS_400, null, HttpStatus.BAD_REQUEST);
        } else {
            return Utils.getBaseResponseDTOResponseEntity(Constantes.HTTP_STATUS_500, e.getMessage(), Constantes.NUMERO_STATUS_500, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}