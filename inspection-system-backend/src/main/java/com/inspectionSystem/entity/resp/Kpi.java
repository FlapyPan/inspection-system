package com.inspectionSystem.entity.resp;

import lombok.Data;

import java.util.List;

@Data
public class Kpi {
    Double kpi;
    List<KpiStruct> kpiStructs;
}
