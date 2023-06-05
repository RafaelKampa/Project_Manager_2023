import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvaliarAlvenariaComponent } from './avaliar-alvenaria.component';

describe('AvaliarAlvenariaComponent', () => {
  let component: AvaliarAlvenariaComponent;
  let fixture: ComponentFixture<AvaliarAlvenariaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvaliarAlvenariaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AvaliarAlvenariaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
