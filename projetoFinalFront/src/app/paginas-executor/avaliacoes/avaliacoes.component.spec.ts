import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvaliacoesComponent } from './avaliacoes.component';

describe('AvaliacoesComponent', () => {
  let component: AvaliacoesComponent;
  let fixture: ComponentFixture<AvaliacoesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AvaliacoesComponent]
    });
    fixture = TestBed.createComponent(AvaliacoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
