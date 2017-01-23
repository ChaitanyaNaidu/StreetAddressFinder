package com.loveholidays.tech.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.loveholidays.tech.test.errors.StreetAddressNotFound;
import com.loveholidays.tech.test.model.StreetAddress;
import com.loveholidays.tech.test.provider.api.AddressFinderResponseJaxb;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by chaitanyanaidu on 1/22/17.
 */
@RestController
public class StreetAddressController {

    @RequestMapping(value="/streetaddress", method = RequestMethod.GET)
    public StreetAddress getStreetName(Model model, @RequestParam(value="postcode", required=true) String postCode) {
        WebClient webClient=null;
        try {
            if(null!=postCode) {
                webClient = WebClient.create("http://ws.postcoder.com/pcw/PCWBJ-6K8FJ-7Q4MN-8QG7X/street/uk/" + postCode + "?format=json");
                Response response = webClient.get();
                response.getStatus();
                response.readEntity(String.class);
                String jsonResponse = (String) response.getEntity();
                ObjectMapper mapper = new ObjectMapper();
                List<AddressFinderResponseJaxb> addressList;
                try {
                    TypeFactory typeFactory = mapper.getTypeFactory();
                    CollectionType collectionType = typeFactory.constructCollectionType(
                            List.class, AddressFinderResponseJaxb.class);
                    addressList = mapper.readValue(jsonResponse, collectionType);
                } catch (IOException e) {
                    //Log the exception for root cause analysis
                    throw new StreetAddressNotFound();
                }
                if (!addressList.isEmpty() && null != addressList.get(0)) {
                    return new StreetAddress(addressList.get(0).getStreet(), addressList.get(0).getSummaryline());
                }
            }
        }finally {
            webClient.close();
        }
        throw new StreetAddressNotFound();
    }

}
