package com.lilypad.ad.mysql.dto;

import com.lilypad.ad.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TableTemplate {
    private String tableName;
    private String level;

    private Map<OpType, List<String>> opTypeFieldSetMap = new HashMap<>();
   // mapping field index to field name
    private Map<Integer, String> posMap = new HashMap<>();
}
