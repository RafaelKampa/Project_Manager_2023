import { TestBed } from '@angular/core/testing';

import { ListarServicosService } from './listar-servicos.service';

describe('ListarServicosService', () => {
  let service: ListarServicosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListarServicosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
