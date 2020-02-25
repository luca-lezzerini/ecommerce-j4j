import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProdottiTaglieComponent } from './prodotti-taglie.component';

describe('ProdottiTaglieComponent', () => {
  let component: ProdottiTaglieComponent;
  let fixture: ComponentFixture<ProdottiTaglieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProdottiTaglieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProdottiTaglieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
