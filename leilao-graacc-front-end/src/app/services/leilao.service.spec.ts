import { TestBed } from '@angular/core/testing';

import { LeilaoService } from './leilao.service';

describe('LeilaoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LeilaoService = TestBed.get(LeilaoService);
    expect(service).toBeTruthy();
  });
});
