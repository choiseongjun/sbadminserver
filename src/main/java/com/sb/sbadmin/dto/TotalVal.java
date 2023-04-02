package com.sb.sbadmin.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TotalVal {
    private int countToday;

    private String sessionLastAccess;
}
