import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvaliarAcabamentoComponent } from './avaliar-acabamento.component';

describe('AvaliarAcabamentoComponent', () => {
  let component: AvaliarAcabamentoComponent;
  let fixture: ComponentFixture<AvaliarAcabamentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvaliarAcabamentoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AvaliarAcabamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
