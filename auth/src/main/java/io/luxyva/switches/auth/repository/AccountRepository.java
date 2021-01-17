package io.luxyva.switches.auth.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.luxyva.switches.auth.domain.Account;

public interface AccountRepository extends BaseMapper<Account> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}