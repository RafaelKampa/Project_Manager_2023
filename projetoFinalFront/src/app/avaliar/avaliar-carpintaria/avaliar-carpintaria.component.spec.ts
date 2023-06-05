import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvaliarCarpintariaComponent } from './avaliar-carpintaria.component';

describe('AvaliarCarpintariaComponent', () => {
  let component: AvaliarCarpintariaComponent;
  let fixture: ComponentFixture<AvaliarCarpintariaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvaliarCarpintariaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AvaliarCarpintariaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
