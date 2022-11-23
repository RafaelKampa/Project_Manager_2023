import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicoHomeComponent } from './servico-home.component';

describe('ServicoHomeComponent', () => {
  let component: ServicoHomeComponent;
  let fixture: ComponentFixture<ServicoHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServicoHomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServicoHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
