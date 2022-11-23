import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarCentrosComponent } from './listar-centros.component';

describe('ListarCentrosComponent', () => {
  let component: ListarCentrosComponent;
  let fixture: ComponentFixture<ListarCentrosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarCentrosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarCentrosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
