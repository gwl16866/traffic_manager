package com.hy.traffic.document.service.impl;

import com.hy.traffic.document.entity.Document;
import com.hy.traffic.document.mapper.DocumentMapper;
import com.hy.traffic.document.service.IDocumentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gwl
 * @since 2020-07-25
 */
@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements IDocumentService {

}
