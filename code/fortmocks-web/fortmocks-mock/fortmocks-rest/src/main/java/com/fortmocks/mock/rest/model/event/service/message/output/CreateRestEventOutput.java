package com.fortmocks.mock.rest.model.event.service.message.output;

import com.fortmocks.core.model.Output;
import com.fortmocks.mock.rest.model.event.dto.RestEventDto;

/**
 * @author Karl Dahlgren
 * @since 1.0
 */
public class CreateRestEventOutput implements Output {

    private RestEventDto createdRestEvent;

    public CreateRestEventOutput(RestEventDto createdRestEvent) {
        this.createdRestEvent = createdRestEvent;
    }

    public RestEventDto getCreatedRestEvent() {
        return createdRestEvent;
    }

    public void setCreatedRestEvent(RestEventDto createdRestEvent) {
        this.createdRestEvent = createdRestEvent;
    }
}
