package org.xcolab.client.contest;

import org.xcolab.client.contest.pojo.FocusArea;
import org.xcolab.client.contest.pojo.FocusAreaOntologyTerm;
import org.xcolab.client.contest.pojo.OntologySpace;
import org.xcolab.client.contest.pojo.OntologyTerm;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public class OntologyClient {

    private static final RestService contestService = new RestService("contest-service");

    private static final RestResource<OntologySpace> ontologySpaceResource = new RestResource<>(contestService,
            "ontologySpaces", OntologySpace.TYPES);

    private static final RestResource<OntologyTerm> ontologyTermResource = new RestResource<>(contestService,
            "ontologyTerms", OntologyTerm.TYPES);

    private static final RestResource<FocusArea> focusAreaResource = new RestResource<>(contestService,
            "focusAreas", FocusArea.TYPES);

    private static final RestResource<FocusAreaOntologyTerm> focusAreaOntologyTermResource = new RestResource<>(contestService,
            "focusAreaOntologyTerms", FocusAreaOntologyTerm.TYPES);

    public static List<OntologySpace> getAllOntologySpaces() {
        return ontologySpaceResource.list().execute();
    }

    public static List<OntologyTerm> getAllOntologyTerms() {
        return ontologyTermResource.list()
                .execute();
    }
    public static List<FocusArea> getAllFocusAreas() {
        return focusAreaResource.list()
                .execute();
    }

    public static List<FocusAreaOntologyTerm> getAllFocusAreaOntologyTerms() {
        return focusAreaOntologyTermResource.list()
                .execute();
    }

    public static OntologyTerm getOntologyTerm(long Id_)  {
            return ontologyTermResource.get(Id_)
                    .execute();
    }
    public static FocusArea getFocusArea(long Id_) {
            return focusAreaResource.get(Id_)
                    .execute();
    }
}
