/**
 * Copyright 2018 Airbnb. Licensed under Apache-2.0. See License in the project root for license
 * information.
 */
package com.airbnb.spinaltap.mysql.mutation.mapper;

import com.airbnb.jitney.event.spinaltap.v1.Mutation;
import com.airbnb.jitney.event.spinaltap.v1.MutationType;
import com.airbnb.spinaltap.mysql.mutation.MysqlInsertMutation;
import com.airbnb.spinaltap.mysql.mutation.MysqlMutationMetadata;

class InsertMutationMapper extends ThriftMutationMapper<MysqlInsertMutation> {
  public InsertMutationMapper(String sourceId) {
    super(sourceId);
  }

  public Mutation map(MysqlInsertMutation mutation) {
    MysqlMutationMetadata metadata = mutation.getMetadata();

    return new Mutation(
        MutationType.INSERT,
        metadata.getTimestamp(),
        sourceId,
        metadata.getDataSource().getThriftDataSource(),
        createBinlogHeader(metadata, mutation.getType().getCode()),
        metadata.getTable().getThriftTable(),
        transformToEntity(mutation.getEntity()));
  }
}
