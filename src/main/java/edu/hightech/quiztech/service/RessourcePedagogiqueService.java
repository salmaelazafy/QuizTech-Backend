package edu.hightech.quiztech.service;

import edu.hightech.quiztech.dto.request.CreateRessourceRequest;
import edu.hightech.quiztech.dto.request.UpdateRessourceRequest;
import edu.hightech.quiztech.dto.response.RessourceResponse;
import java.util.List;

public interface RessourcePedagogiqueService {
    RessourceResponse createRessource(CreateRessourceRequest request);
    RessourceResponse updateRessource(Long id, UpdateRessourceRequest request);
    RessourceResponse getRessourceById(Long id);
    List<RessourceResponse> getRessourcesByClasse(Long classeId);
    void deleteRessource(Long id);
}