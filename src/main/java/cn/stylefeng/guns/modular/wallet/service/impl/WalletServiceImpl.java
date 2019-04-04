package cn.stylefeng.guns.modular.wallet.service.impl;

import cn.stylefeng.guns.modular.system.model.Wallet;
import cn.stylefeng.guns.modular.system.dao.WalletMapper;
import cn.stylefeng.guns.modular.wallet.service.IWalletService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhaohe
 * @since 2019-04-04
 */
@Service
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet> implements IWalletService {

    @Autowired
    private WalletMapper walletMapper;
    @Override
    public List<Map<String, Object>> selectLists(String phone) {
        return walletMapper.selectLists(phone);
    }
}
