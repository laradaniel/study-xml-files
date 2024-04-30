package com.projecttest.service;

import com.projecttest.model.XmlData;
import com.projecttest.repository.XmlRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class SimulatorService {

    XmlRepository xmlRepository;

    public SimulatorService(XmlRepository xmlRepository) {
        this.xmlRepository = xmlRepository;
    }

    public byte[] findAllTransactions(){
        List<XmlData> allXml = xmlRepository.findAll();
        if(allXml.isEmpty()) return new byte[0];
        return createZip(allXml);
    }
    public void save(String xmlContent){
        XmlData xmlData = new XmlData();
        xmlData.setContent(xmlContent);
        xmlRepository.save(xmlData);
    }

    private byte[] createZip(List<XmlData> xmlList) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(baos);

            for (XmlData xmlData : xmlList) {
                ZipEntry entry = new ZipEntry("xml_" + xmlData.getId() + ".xml");
                zos.putNextEntry(entry);
                zos.write(xmlData.getContent().getBytes());
                zos.closeEntry();
            }

            zos.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
