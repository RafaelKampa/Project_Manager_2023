import { TestBed } from '@angular/core/testing';

import { ListarCentrosService } from './listar-centros.service';

describe('ListarCentrosService', () => {
  let service: ListarCentrosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListarCentrosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
