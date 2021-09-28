package site.aoba.devicemanagement2api.domain.services;

import site.aoba.devicemanagement2api.architecture.domain.service.IService;

public interface ICheckDeviceTypeDeleteService extends IService {
    boolean check(long id);
}
