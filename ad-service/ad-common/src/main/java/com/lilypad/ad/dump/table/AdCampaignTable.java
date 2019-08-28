package com.lilypad.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdCampaignTable {
    private Long id;
    private Long userId;
    private Integer planStatus;
    private Date startDate;
    private Date endDate;
}
