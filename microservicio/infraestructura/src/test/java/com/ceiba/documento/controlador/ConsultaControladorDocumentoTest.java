package com.ceiba.documento.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.categoria.controlador.ConsultaControladorCategoria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ConsultaControladorDocumento.class)
public class ConsultaControladorDocumentoTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void listar() throws Exception {
        mocMvc.perform(get("/documentos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
