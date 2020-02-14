import { TestBed } from '@angular/core/testing';

import { AreaComuneService } from './area-comune.service';

describe('AreaComuneService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AreaComuneService = TestBed.get(AreaComuneService);
    expect(service).toBeTruthy();
  });
});
