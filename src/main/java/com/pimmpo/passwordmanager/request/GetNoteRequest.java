package com.pimmpo.passwordmanager.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mavrin
 * @created 25.05.2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetNoteRequest {
    private long noteId;
}
